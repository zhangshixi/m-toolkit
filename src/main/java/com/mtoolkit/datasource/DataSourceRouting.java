package com.mtoolkit.datasource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Naming data source routing.
 * Using which data source by the name witch binded in the current thread.
 * 
 * we can configure the the data source as following: 
 * <pre>
 * &lt;bean id="abstractDataSource"
 *       class="org.springframework.jdbc.datasource.DriverManagerDataSource"
 *       abstract="true"&gt;
 *     &lt;property name="driverClassName" value="${jdbc.driver}" /&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="readDataSource" parent="abstractDataSource"&gt;
 *     &lt;property name="url" value="${read.jdbc.url}" /&gt;
 *     &lt;property name="username" value="${read.jdbc.username}" /&gt;
 *     &lt;property name="password" value="${read.jdbc.password}" /&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="writeDataSource" parent="abstractDataSource"&gt;
 *     &lt;property name="url" value="${write.jdbc.url}" /&gt;
 *     &lt;property name="username" value="${write.jdbc.username}" /&gt;
 *     &lt;property name="password" value="${write.jdbc.password}" /&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="dataSourceLookup"
 *       class="org.springframework.jdbc.datasource.lookup.MapDataSourceLookup"&gt;
 *     &lt;property name="dataSources"&gt;
 *         &lt;map&gt;
 *             &lt;entry key="readDataSource" value-ref="readDataSource" /&gt;
 *             &lt;entry key="writeDataSource" value-ref="writeDataSource" /&gt;
 *         &lt;/map&gt;
 *     &lt;/property&gt;
 * &lt;/bean&gt;
 * 
 * &lt;bean id="dataSource"
 *       class="com.zhangsx.toolkit.datasource.DataSourceRouting"&gt;
 *     &lt;property name="defaultTargetDataSource" ref="writeDataSource" /&gt;
 *     &lt;property name="dataSourceLookup" ref="dataSourceLookup" /&gt;
 *     &lt;property name="targetDataSources"&gt;
 *         &lt;map&gt;
 *             &lt;entry key="read" value="readDataSource" /&gt;
 *             &lt;entry key="write" value="writeDataSource" /&gt;
 *         &lt;/map&gt;
 *     &lt;/property&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="propertiesConfigurer"
 *       class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer"&gt;
 *     &lt;property name="location" value="classpath:/dataSource.properties" /&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 02/02/2012
 * @since 	JDK1.5
 * 
 * @see		com.mtoolkit.datasource.DataSourceHolder
 */
public class DataSourceRouting extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getCurrentDataSource();
	}

	/**
	 * @since 1.7
	 */
	//@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}

}