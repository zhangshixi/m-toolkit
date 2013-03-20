package com.mtoolkit.pool;

/**
 * Object manager interface.
 * 
 * @param	<T> object type.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface ObjectManager<T> {
	
	/**
	 * Creates an object instance.
	 * 
	 * @return the newly created object instance.
	 * 
	 * @throws ObjectPoolException if an exception occurs while creating.
	 */
	public T createObject() throws ObjectPoolException;
	
	/**
	 * Destroys the target object instance.
	 * 
	 * @param  target the object instance witch for destroyed.
	 * 
	 * @throws ObjectPoolException if an exception occurs while destroying.
	 */
	public void destroyObject(T target) throws ObjectPoolException;
	
	/**
	 * Validates the legality of the target object instance.
	 * 
	 * @param  target object instance witch for validated.
	 * 
	 * @return {@code true} if an only if the target object is legality;
	 * 		   {@code false} otherwise.
	 * 
	 * @throws ObjectPoolException if an exception occurs while validating.
	 */
	public boolean validateObject(T target) throws ObjectPoolException;
	
	/**
	 * Activates the target object instance.
	 * 
	 * @param  target object instance witch for activated.
	 * 
	 * @throws ObjectPoolException if an exception occurs while activating.
	 */
	public void activateObject(T target) throws ObjectPoolException;
	
	/**
	 * Passivates the target object instance.
	 * 
	 * @param  target object instance witch for passivated.
	 * 
	 * @throws ObjectPoolException if an exception occurs while passivating.
	 */
	public void passivateObject(T target) throws ObjectPoolException;
	
}