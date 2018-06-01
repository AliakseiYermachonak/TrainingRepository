package com.epam.task.two.text.exceptions;

public class WTFException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WTFException() {
		super();
	}

	public WTFException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WTFException(String message, Throwable cause) {
		super(message, cause);
	}

	public WTFException(String message) {
		super(message);
	}

	public WTFException(Throwable cause) {
		super(cause);
	}
	
}
