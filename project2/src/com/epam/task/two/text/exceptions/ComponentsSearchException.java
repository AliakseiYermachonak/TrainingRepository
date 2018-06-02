package com.epam.task.two.text.exceptions;

public class ComponentsSearchException extends Exception{

	/**
	 * Exception is thrown when a component of
	 * a composition tree could not be found.
	 * @author Alexey Yermachyonok
	 * @version 1.0
	 */
	
	private static final long serialVersionUID = 1L;

	public ComponentsSearchException() {
		super();
	}

	public ComponentsSearchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ComponentsSearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComponentsSearchException(String message) {
		super(message);
	}

	public ComponentsSearchException(Throwable cause) {
		super(cause);
	}
	
	
	
}
