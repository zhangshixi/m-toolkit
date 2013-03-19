package com.mtoolkit.proxy;

/**
 * Method intercepter interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface Interceptor {
	
	/**
	 * Intercepts the target method invocation.
	 * 
	 * @param  invocation the method invocation.
	 * 
	 * @return the return value of invocation after intercepts.
	 * 
	 * @throws Throwable any exception or error that was thrown whiling intercept.
	 */
	public Object intercept(Invocation invocation) throws Throwable;
	
}

