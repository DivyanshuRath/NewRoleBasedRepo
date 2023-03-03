package com.deloitte.newproject.exception;

/**
 * This is ResourceNotFoundException class inherits properties and behaviors from RuntimeException class
 */
public class ResourceNotFoundException extends RuntimeException{

    /**
     * This method manipulates the exception message
     * @param message The exception message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
