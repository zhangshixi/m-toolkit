package com.mtoolkit.proxy;

import java.lang.reflect.Method;

/**
 * Method invocation interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface Invocation {
	
	/**
	 * Gets the proxy object on which invocation was invoked.
	 * 
	 * @return the proxy object on which invocation was invoked.
	 */
	public Object getProxy();
	
	/**
	 * Gets the method being called.
	 * 
	 * @return the method being called.
	 */
	public Method getMethod();
	
	/**
	 * Gets the arguments being passed to this method invocation. 
	 * 
	 * @return the arguments being passed to this method invocation.
	 */
	public Object[] getArguments();
	
	/**
	 * Called in order to let the invocation proceed.
	 * 
	 * @return the return value of the invocation.
	 * 
	 * @throws Throwable any exception or error that was thrown whiling call.
	 */
	public Object proceed() throws Throwable;
	
}

