package com.deloitte.newproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * This is a GlobalExceptionHandler class annotated with @ControllerAdvice.
 * This class defines the ExceptionHandler method for ResourceNotFoundException.
 * ControllerAdvice annotation helps to integrate multiple exception handlers into a single global unit.
 */
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    /**
     * This method creates an error response with a concise message
     * @param ex The exception itself
     * @param request The request
     * @return The response entity status
     */
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity handleResourceNotFoundException( Exception ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
