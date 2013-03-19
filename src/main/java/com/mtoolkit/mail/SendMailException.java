package com.mtoolkit.mail;

public class SendMailException extends RuntimeException {

	/** serial version UID */
	private static final long serialVersionUID = 3637226464108526637L;

	public SendMailException() {
		super();
	}

	public SendMailException(String message) {
		super(message);
	}

	public SendMailException(Throwable cause) {
		super(cause);
	}
	
	public SendMailException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
