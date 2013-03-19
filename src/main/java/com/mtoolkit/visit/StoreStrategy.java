package com.mtoolkit.visit;

/**
 * Visit number store strategy interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 1/31/2012
 * @since 	JDK1.5
 */
public interface StoreStrategy {
	
	/**
	 * Store the specified key and visit value.
	 * 
	 * @param  key store key.
	 * @param  value visit number.
	 * 
	 * @throws Exception if any exception occurs whiling store the value. 
	 */
	public void store(String key, int value) throws Exception;
	
}

