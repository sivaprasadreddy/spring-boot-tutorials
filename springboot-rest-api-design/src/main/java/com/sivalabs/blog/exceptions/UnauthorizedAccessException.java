/**
 * 
 */
package com.sivalabs.blog.exceptions;

/**
 * @author Siva
 *
 */
public class UnauthorizedAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedAccessException() {
	}

	public UnauthorizedAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedAccessException(String message) {
		super(message);
	}

	public UnauthorizedAccessException(Throwable cause) {
		super(cause);
	}
	
}
