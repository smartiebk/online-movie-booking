package com.ombp.cloud.app.ui.exception;

public class ServiceDownIOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 682704604389258777L;

	
	/**
     * Constructor
     *
     */
	public ServiceDownIOException() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param message .
	 */
	public ServiceDownIOException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause .
	 */
	public ServiceDownIOException(Throwable cause) {
		super(cause);
	}
	/**
	 * Constructor
	 * 
	 * @param message .
	 * @param cause .
	 */
	public ServiceDownIOException(String message,Throwable cause) {
		super(message,cause);
	}
}
