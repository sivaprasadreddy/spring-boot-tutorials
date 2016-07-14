/**
 * 
 */
package com.sivalabs.blog.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Siva
 *
 */
public class ServiceResponse<T> extends ResponseEntity<T>
{
	private String errorMessage;
	private String devErrorMessage;
	private List<String> errors;

	public ServiceResponse()
	{
		super(HttpStatus.OK);
	}
	public ServiceResponse(HttpStatus status)
	{
		super(status);
	}
	public ServiceResponse(T data)
	{
		super(data, HttpStatus.OK);
	}
	public ServiceResponse(HttpStatus status, String errorMessage, String devErrorMessage, List<String> errors)
	{
		super(status);
		this.errorMessage = errorMessage;
		this.devErrorMessage = devErrorMessage;
		this.errors = errors;
	}
	public ServiceResponse(HttpStatus status, String errorMessage, String devErrorMessage, List<String> errors,
			T data)
	{
		super(data,status);
		this.errorMessage = errorMessage;
		this.devErrorMessage = devErrorMessage;
		this.errors = errors;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public String getDevErrorMessage()
	{
		return devErrorMessage;
	}

	public void setDevErrorMessage(String devErrorMessage)
	{
		this.devErrorMessage = devErrorMessage;
	}

	public List<String> getErrors()
	{
		return errors;
	}

	public void setErrors(List<String> errors)
	{
		this.errors = errors;
	}

	public void addError(String error)
	{
		if (this.errors == null)
		{
			this.errors = new ArrayList<>();
		}
		this.errors.add(error);
	}

}
