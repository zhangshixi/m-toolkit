package com.mtoolkit.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.mlogger.Loggers;
import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invocation;
import com.mtoolkit.util.CommonUtil;

/**
 * DataSource selection intercepter.
 *
 * <p> 该拦截器的作用是可根据拦截器配置，将选择不同的数据源
 * 织入到Bean的不同方法上，从而实现在不同方法间的数据源切换功能。
 * 
 * <p> Examples：
 * <pre>
 *      <bean id="dataSourceInterceptor"
 *          class="com.etnetchina.datasource.DataSourceInterceptor">
 *          <property name="dataSourceLevels">
 *              <map>
 *                  <entry key="read" value="1" />
 *                  <entry key="write" value="2" />
 *              </map>
 *          </property>
 *
 *          <property name="dataSourceAttributes">
 *              <props>
 *                  <prop key="update*">write</prop>
 *                  <prop key="query*">read</prop>
 *                  <prop key="*">read</prop>
 *              </props>
 *          </property>
 *      </bean>
 *
 *      <bean id="autoProxy"
 *          class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
 *          <property name="beanNames" value="*Business" />
 *          <property name="interceptorNames">
 *              <list>
 *                  <value>dataSourceInterceptor</value>
 *              </list>
 *          </property>
 *      </bean>
 * </pre>
 *
 * <p> 这样就实现了将此拦截器织入到所有以{@code Business}结尾的Bean上，并根据其方法名称的不同
 * 来进行数据源切换，在以{@code update}开头的方法中使用{@code write}数据源，以{@code query}
 * 开头的方法中使用{@code read}数据源，其余方法中也使用{@code read}数据源。
 * {@code dataSourceLevels}属性指定了配置的数据源及其对应的级别，未显示声明的将认为默认级别为0；
 * 在方法执行过程中，如果调用方法线程已经绑定了一个数据源，那么会拿此数据源与该执行方法配置需要的数据源
 * 的级别进行比较，如果方法配置的数据源级别更高，那么将抛出异常{@link IllegalStateException}。
 *
 * <p> 方法名称的通用配置使用通配符{@code *}表示，目前只支持最多一个通配符的方法名称，
 * 如{@code up*date}，形如{@code up*date*User}之类的方法名称配置将不受支持。
 *
 * <p> 在进行数据源的选择时，会先检查当前线程是否已经绑定了一个指定的数据源，如果当前线程已
 * 绑定了一个数据源，那么在该线程调用的子方法内，不会再根据配置进行数据源的选择，而是一直延用已经
 * 选择了的数据源；在一个线程的方法调用结束时，需要将数据源和线程的绑定关系解除，这时，只有实现绑定
 * 了数据源的方法才会去解除此绑定关系，延用已经绑定的方法不会去做解除绑定操作。
 *
 * <p> 另外，拦截器的执行是有一定的顺序的，数据源选择拦截器必须配置在事务拦截器之前进行。
 * 多数据源路由配置请参见{@link com.mtoolkit.datasource.DataSourceRouting}。
 *
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 01/21/2012
 * @since 	JDK1.5
 * 
 * @see 	com.mtoolkit.datasource.DataSourceRouting
 */
public class DataSourceInterceptor implements Interceptor {

	/** other data source name */
    private String otherDataSource = null;
   	/** data source levels */
    private Map<String, Integer> dataSourceLevels;
    /** data source attributes */
    private Map<String, String> dataSourceAttributes;
    //方法名称通配符。
    private static final String METHOD_WILDCARD = "*";
    /** default data source level */
    private static final int DEFAULT_DATA_SOURCE_LEVEL = 0;

    /** logger */
    private static final Loggers LOGGER = Loggers.getLoggers(Interceptor.class);

    /**
     * 设置数据源等级信息，未设置的默认等级为0。
     * @param dataSourceLevels 数据源等级信息。
     */
    public void setDataSourceLevels(Map<String, Integer> dataSourceLevels) {
        this.dataSourceLevels = dataSourceLevels;
        LOGGER.info("Data source levels are: {0}", dataSourceLevels);
    }

    /**
     * 设置数据源配置定义信息。
     * @param attributes 数据源配置属性文件。
     */
    public void setDataSourceAttributes(Properties attributes) {
        if (attributes == null) {
            throw new IllegalArgumentException(
                    "Not set any data source attributs for DataSourceInterceptor.");
        }

        dataSourceAttributes = new HashMap<String, String>(
                CommonUtil.getMapCapacity(attributes.size()));

        for (Entry<Object, Object> entry : attributes.entrySet()) {
            validateAttribute(entry.getKey(), entry.getValue());

            if (METHOD_WILDCARD.equals(entry.getKey())) {
                otherDataSource = (String) entry.getValue();
            } else {
                dataSourceAttributes.put((String) entry.getKey(), (String) entry.getValue());
            }
        }

        LOGGER.info("Data source interceptor config is [{0}].", dataSourceAttributes);
        LOGGER.info("Other default data source is [*={0}].", otherDataSource);
    }

    /**
     * 实际方法调用。
     * @param invocation 方法调用信息。
     * @return 方法调用后的返回结果。
     * @throws Throwable 方法调用异常。
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final String methodName = invocation.getMethod().getName();

        String methodDataSource = getMethodDataSource(methodName);
        String currentDataSource = DataSourceHolder.getCurrentDataSource();

        boolean selected = false; //标记当前调用线程是否已经选择了数据源。
        if (currentDataSource != null) {
            selected = true;
            LOGGER.debug("Current thread already selected date source [{0}].",
                    currentDataSource);

            checkDataSourceLevel(currentDataSource, methodDataSource);
        } else {
            DataSourceHolder.setCurrentDataSource(methodDataSource);

            LOGGER.debug("The method of [{0}] selected data source [{1}] by router.",
                    methodName, methodDataSource);
        }

        Object result = null;
        try {
            result = invocation.proceed();
        } finally {
            if (!selected) { //只有自己选择的数据源才会自己清除。
            	DataSourceHolder.removeDataSources();
                LOGGER.debug("The method of [{0}] cleared the data source [{1}].",
                        methodName, methodDataSource);
            }
        }

        return result;
    }

    /**
     * 检查绑定在当前线程和当前方法上的数据源级别，如果当前方法需要的数据源级别高于当前线程上的数据源，
     * 将抛出非法访问异常。
     * @param currentDataSource 绑定在当前线程上的数据源名称。
     * @param methodDataSource 当前方法执行需要的数据源名称。
     * @throws IllegalStateException 如果当前方法需要的数据源级别高于当前线程上的数据源。
     */
    private void checkDataSourceLevel(
            String currentDataSource, String methodDataSource) {
        int currentDataSourceLevel = getDataSourceLevel(currentDataSource);
        int methodDataSourceLevel = getDataSourceLevel(methodDataSource);

        LOGGER.debug("Current thread data source level is [{0}] and method data source is [{1}].",
                currentDataSourceLevel, methodDataSourceLevel);

        if (methodDataSourceLevel > currentDataSourceLevel) {
            IllegalStateException ex = new IllegalStateException(
                    "Bound current thread data source level must not less than current method data source level.");
            LOGGER.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * 获取指定方法名称绑定的数据源配置名称。
     * @param methodName 方法名称。
     * @return 绑定在该方法上的数据源配置名称，如果未指定，将返回默认的其他配置。
     */
    private String getMethodDataSource(String methodName) {
        if (dataSourceAttributes.isEmpty() && otherDataSource != null) {
            return otherDataSource;

        } else {
            return matcherDataSource(methodName, dataSourceAttributes);
        }
    }

    /**
     * 校验数据源属性配置的合法性。
     * @param key 方法名称匹配配置key。
     * @param value 方法名称对应的数据源名称value。
     * @throws IllegalArgumentException 属性参数配置不合法。
     *  如果key为null或空字符串、或包行一个以上的通配符"*"、或value为null或空字符串。
     */
    private void validateAttribute(Object key, Object value)
            throws IllegalArgumentException {
        if (key == null || key.toString().isEmpty()) {
            throw new IllegalArgumentException(
                    "The key of data source attribute must not be null.");
        }

        if (value == null || value.toString().isEmpty()) {
            throw new IllegalArgumentException(
                    "The value of data source attribute must not be null.");
        }

        final String keyString = (String) key;
        if (keyString.contains(METHOD_WILDCARD)
                && keyString.indexOf(METHOD_WILDCARD)
                != keyString.lastIndexOf(METHOD_WILDCARD)) {
            throw new IllegalArgumentException(
                    "The key of data source attribute must not contains more than one method wildcard \"*\".");
        }
    }

    /**
     * 判断给定的方法名称是否在数据源拦截器的属性配置中存在。
     * 如果存在返回配置中对应的数据源名称，不存在将返回其他数据源的配置。
     * @param methodName 当前调用的方法名称。
     * @param attributes 数据源拦截器的方法配置属性集合。
     */
    private String matcherDataSource(
            String methodName, Map<String, String> attributes) {
        for (Entry<String, String> entry : attributes.entrySet()) {
            if (isMatcher(methodName, entry.getKey())) {
                return entry.getValue();
            }
        }

        return otherDataSource;
    }

    /**
     * 判断给定的方法名称是否匹配给定的配置属性。
     * @param methodName 当前调用的方法名称。
     * @param attribute 配置属性。
     * @return 当前方法名称是否匹配给定的配置属性。
     *      <code>true</code>代表匹配，<code>false</code>代表不匹配。
     */
    private boolean isMatcher(String methodName, String attribute) {
        int index = attribute.indexOf(METHOD_WILDCARD);

        if (index != -1) {
            String prefix = attribute.substring(0, index);
            String suffix = attribute.substring(index + 1, attribute.length());

            if (index == 0) {
                return methodName.endsWith(suffix); //后缀匹配。

            } else if (index == attribute.length()) {
                return methodName.startsWith(prefix); //前缀匹配。

            } else {
                return methodName.startsWith(prefix)
                        && methodName.endsWith(suffix); //前后缀匹配。
            }

        } else {
            return methodName.equals(attribute); //无通配符，名称匹配。
        }
    }

    /**
     * 获取配置的指定数据源名称的级别，如果不存在将返回0。
     * @param dataSourceName 数据源名称。
     * @return 数据源名称对应的级别，不存在将返回0。
     */
    private int getDataSourceLevel(String dataSourceName) {
        if (dataSourceLevels == null) {
            return DEFAULT_DATA_SOURCE_LEVEL;
        } else {
            Integer level = dataSourceLevels.get(dataSourceName);
            return level == null ? DEFAULT_DATA_SOURCE_LEVEL : level.intValue();
        }
    }
}

