/**
 * 
 */
package com.sivalabs.blog.web.interceptors;

/**
 * @author Siva
 *
 */
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sivalabs.blog.exceptions.ValidationFailureException;
import com.sivalabs.blog.model.ServiceResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ValidationFailureException.class)
    public ServiceResponse<Void> validationException(ValidationFailureException e) {
        logger.error("Validation Exception occured: "+e.getMessage());
        ServiceResponse<Void> response = new ServiceResponse<>(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(e.getMessage());
        Errors errors = e.getErrors();
        if (errors.hasErrors()) {
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError objectError : allErrors) {
                response.addError(objectError.getDefaultMessage());
            }
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        response.setDevErrorMessage(sw.toString());
        return response;
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ServiceResponse<Void> servletRequestBindingException(ServletRequestBindingException e) {
        logger.error("ServletRequestBindingException occured: "+e.getMessage());
        ServiceResponse<Void> response = new ServiceResponse<Void>(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(e.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        response.setDevErrorMessage(sw.toString());
        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ServiceResponse<Void> badCredentialsException(BadCredentialsException e) {
        logger.error("Bad Credentials Exception");
        return new ServiceResponse<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ServiceResponse<Void> exception(Exception e) {
        logger.error("Exception occured: "+e.getMessage(), e);
        ServiceResponse<Void> response = new ServiceResponse<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setErrorMessage(e.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        response.setDevErrorMessage(sw.toString());
        return response;
    }
}
