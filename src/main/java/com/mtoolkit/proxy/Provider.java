package com.mtoolkit.proxy;

/**
 * Object provider interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface Provider {
	
	/**
	 * Provides an object to a delegating proxy.
	 * 
	 * @return an object to a delegating proxy.
	 */
	public Object getObject();

}

