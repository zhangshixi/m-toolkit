package com.mtoolkit.pool;

/**
 * Object pool exception.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public class ObjectPoolException extends Exception {

	/** serial version id */
	private static final long serialVersionUID = -8097521325327151512L;

	public ObjectPoolException() {
		super();
	}
	
	public ObjectPoolException(String message) {
		super(message);
	}
	
	public ObjectPoolException(Throwable cause) {
		super(cause);
	}

	public ObjectPoolException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

