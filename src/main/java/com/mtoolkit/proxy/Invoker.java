package com.mtoolkit.proxy;

import java.lang.reflect.Method;

/**
 * Method invoker interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface Invoker {
	
	/**
	 * Invokes the target method of the proxy object with specified arguments.
	 * 
	 * @param  proxy  the proxy object.
	 * @param  method the method being invoked.
	 * @param  args   the method arguments.
	 * 
	 * @return the return value.
	 * 
	 * @throws Throwable any exception or error that was thrown whiling invoke.
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}

