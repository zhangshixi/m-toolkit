package com.mtoolkit.mybatis;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.mlogger.Loggers;

/**
 * Mybatis SQL execute logger interceptor.
 * 
 * @author Michael
 */
@Intercepts
({
    @Signature(type=Executor.class, method="update", args={MappedStatement.class, Object.class}),
    @Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MybatisLogInterceptor implements Interceptor {
	
	/** SQL slow execute time */
	private long slowExecuteTime = DEFAULT_SQL_SLOW_EXECUTE_TIME;
	/** SQL default slow execute time */
	public static final long DEFAULT_SQL_SLOW_EXECUTE_TIME = 100L;
	
	/** logger utility */
    private static final Loggers logger = Loggers.getLoggers(MybatisLogInterceptor.class);
    
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		long startTime = System.currentTimeMillis();
		
		Object result = invocation.proceed();
		
		long executeTime = System.currentTimeMillis() - startTime;
		if (executeTime < slowExecuteTime) {
			logger.debug("<== Sql executed time: [{0}] ms", executeTime);
		} else {
			logger.warn("<== Sql executed time is too slow: [{0}] ms", executeTime);
		}
		
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties props) {
		String slowTime = props.getProperty("slowExecuteTime");
		if (slowTime != null && !slowTime.isEmpty()) {
			this.slowExecuteTime = Long.parseLong(slowTime);
		}
	}
	
	public long getSlowExecuteTime() {
		return slowExecuteTime;
	}
	
	public void setSlowExecuteTime(long slowExecuteTime) {
		this.slowExecuteTime = slowExecuteTime;
	}

}
