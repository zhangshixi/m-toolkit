package com.mtoolkit.visit;

/**
 * Visit manager.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/14/2011
 * @since 	JDK1.5
 */
public interface VisitManager {
	
	/**
	 * Increases the value with the specified key.
	 * 
	 * @param key target key.
	 * @param value increment value.
	 */
	public void increase(String key, int value);
	
	/**
	 * Decreases the value with the specified key.
	 * 
	 * @param key target key.
	 * @param value decrement value.
	 */
	public void decrease(String key, int value);
	
}

