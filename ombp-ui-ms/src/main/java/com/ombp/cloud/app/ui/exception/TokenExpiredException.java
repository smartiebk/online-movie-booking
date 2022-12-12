package com.ombp.cloud.app.ui.exception;

public class TokenExpiredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 682704604389258777L;

	
	/**
     * Constructor
     *
     */
	public TokenExpiredException() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param message .
	 */
	public TokenExpiredException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param cause .
	 */
	public TokenExpiredException(Throwable cause) {
		super(cause);
	}
	/**
	 * Constructor
	 * 
	 * @param message .
	 * @param cause .
	 */
	public TokenExpiredException(String message,Throwable cause) {
		super(message,cause);
	}
}
