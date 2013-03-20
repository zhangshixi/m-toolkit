package com.mtoolkit.pool.support;

import com.mtoolkit.pool.ObjectManager;
import com.mtoolkit.pool.ObjectPoolException;

/**
 * Abstract object manager implementation.
 * 
 * @param 	<T> object type.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public abstract class AbstractObjectManager<T> implements ObjectManager<T> {

	/**
	 * {@inheritDoc}
	 * Abstract method for subclasses implementation.
	 * 
	 * @returns {@inheritDoc}
	 * 
	 * @throws ObjectPoolException {@inheritDoc}
	 */
	@Override
	public abstract T createObject() throws ObjectPoolException;

	/**
	 * {@inheritDoc}
	 * Do nothing for the object instance.
	 * 
	 * @param target {@inheritDoc}
	 * 
	 * @throws ObjectPoolException {@inheritDoc}
	 */
	@Override
	public void destroyObject(T target) throws ObjectPoolException {
	}

	/**
	 * {@inheritDoc}
	 * Always return {@code true}.
	 * 
	 * @param target {@inheritDoc}
	 * 
	 * @throws ObjectPoolException {@inheritDoc}
	 */
	@Override
	public boolean validateObject(T target) throws ObjectPoolException {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * do nothing for the object instance.
	 * 
	 * @param target {@inheritDoc}
	 * 
	 * @throws ObjectPoolException {@inheritDoc}
	 */
	@Override
	public void activateObject(T target) throws ObjectPoolException {
	}

	/**
	 * {@inheritDoc}
	 * do nothing for the object instance.
	 * 
	 * @param target {@inheritDoc}
	 * 
	 * @throws ObjectPoolException {@inheritDoc}
	 */
	@Override
	public void passivateObject(T target) throws ObjectPoolException {
	}

}