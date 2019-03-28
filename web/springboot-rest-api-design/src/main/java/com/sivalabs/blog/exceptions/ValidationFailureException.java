/**
 * 
 */
package com.sivalabs.blog.exceptions;

/**
 * @author Siva
 *
 */
import org.springframework.validation.Errors;

public class ValidationFailureException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	private Errors errors;
	public ValidationFailureException(Errors errors)
	{
		this.errors = errors;
	}
	public Errors getErrors()
	{
		return errors;
	}
}
