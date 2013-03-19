package com.mtoolkit.datasource;

/**
 * Data source hold manager.
 * Use {@link java.lang.ThreadLocal} bind the data source to the thread.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 02/02/2012
 * @since 	JDK1.5
 * 
 * @see		java.lang.ThreadLocal
 */
public class DataSourceHolder {
	
	private static ThreadLocal<String> _dataSources = new ThreadLocal<String>();
	
	public static void setCurrentDataSource(String name) {
		_dataSources.set(name);
	}
	
	public static String getCurrentDataSource() {
		return _dataSources.get();
	}
	
	public static void removeDataSources() {
		_dataSources.remove();
	}
	
}

