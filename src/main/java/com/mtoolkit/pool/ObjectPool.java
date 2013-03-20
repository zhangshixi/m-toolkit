package com.mtoolkit.pool;

/**
 * Object pool interface.
 * 
 * @param 	<T> object type. 
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface ObjectPool<T> {
	
	/**
	 * Borrows an object instance from the object pool.
	 * 
	 * @return an object instance.
	 * 
	 * @throws ObjectPoolException if an exception occurs while borrowing.
	 */
	public T borrowObject() throws ObjectPoolException;
	
	/**
	 * Returns the target object instance to the object pool.
	 * 
	 * @param  target object instance which will be returned to pool.
	 * 
	 * @throws ObjectPoolException if an exception occurs while returning.
	 */
	public void returnObject(T target) throws ObjectPoolException;
	
	/**
	 * Invalids the target object instance.
	 * 
	 * @param  target object instance which will be invalid.
	 * 
	 * @throws ObjectPoolException if an exception occurs whiling invalid.
	 */
	public void invalidObject(T target) throws ObjectPoolException;
	
	/**
	 * Gets the idle number of object in the object pool.
	 * 
	 * @return object idle number.
	 */
	public int getIdleNumber();
	
	/**
	 * Gets the active number of object in the object pool.
	 * 
	 * @return object active number.
	 */
	public int getActiveNumber();
	
	/**
	 * Clear the object pool.
	 * 
	 * @throws ObjectPoolException if an exception occurs while clearing.
	 */
	public void clear() throws ObjectPoolException;
	
	/**
	 * Close the object pool.
	 * 
	 * @throws ObjectPoolException if an exception occurs while closing.
	 */
	public void close() throws ObjectPoolException;
	
}