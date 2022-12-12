package com.ombp.cloud.app.ui.exception;

public class CookieMalformedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 682704604389258777L;

	
	/**
     * Constructor
     *
     */
	public CookieMalformedException() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param message .
	 */
	public CookieMalformedException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause .
	 */
	public CookieMalformedException(Throwable cause) {
		super(cause);
	}
	/**
	 * Constructor
	 * 
	 * @param message .
	 * @param cause .
	 */
	public CookieMalformedException(String message,Throwable cause) {
		super(message,cause);
	}
}
