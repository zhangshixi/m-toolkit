package com.mtoolkit.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;

import com.mlogger.Loggers;
import com.mtoolkit.page.Dialect;
import com.mtoolkit.page.MysqlDialect;
import com.mtoolkit.page.Page;

/**
 * @author Administrator
 */
@Intercepts({
    @Signature(type=StatementHandler.class, method="prepare", args=Connection.class)
})
public class MybatisPageInterceptor implements Interceptor {

    /** page parameter name */
    public static final String PARAMETER_PAGE = "page";
    /** page parameter holder */
    public static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<Page<?>>();
    
    /** logger utility */
    private static final Loggers logger = Loggers.getLoggers(MybatisPageInterceptor.class);
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statement = (StatementHandler) invocation.getTarget();
        MetaObject metaData = MetaObject.forObject(statement);
        
        Page<?> page = getPageVariable(metaData);
        if (page == null) {
            return invocation.proceed();
        } else {
            PAGE_HOLDER.set(page);
            
            BoundSql boundSql = statement.getBoundSql();
            String targetSql = boundSql.getSql();
            Dialect dialect = getDialectVariable(metaData);
            
            if (page.isAutoCount()) {
                // auto count total data
                Connection connection = (Connection) invocation.getArgs()[0];
                String countSql = dialect.getCountSql(targetSql);
                
                int totalData = selectPageTotalData(connection, countSql);
                page.setTotalData(totalData);
            }
            
            String executeSql = dialect.getLimitSql(targetSql, page);
            metaData.setValue("delegate.boundSql.sql",     executeSql);
            metaData.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaData.setValue("delegate.rowBounds.limit",  RowBounds.NO_ROW_LIMIT);
            
            Object result = invocation.proceed();
            
            PAGE_HOLDER.remove();
            
            return result;
        }
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties props) {
        // do nothing now
    }
    
    // ---- private methods ---------------------------------------------------------------
    private Page<?> getPageVariable(MetaObject metaData) {
        ParameterHandler parameter = (ParameterHandler) metaData.getValue("delegate.parameterHandler");
        Object targetParam = parameter.getParameterObject();
        Page<?> page = null;
        if (targetParam instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> paramMap = (Map<String, Object>) targetParam;
            page = (Page<?>) paramMap.get(PARAMETER_PAGE);
            logger.debug("==> Query page: {0}", page);
        }
        
        return page;
    }

    private Dialect getDialectVariable(MetaObject metaData) {
        // TODO Auto-generated method stub
        return new MysqlDialect();
    }

    private int selectPageTotalData(Connection connection, String countSql) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        
        try {
            statement = connection.prepareStatement(countSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
                break;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        
        return count;
    }
	
}
