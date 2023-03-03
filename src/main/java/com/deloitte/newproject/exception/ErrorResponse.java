package com.deloitte.newproject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a custom ErrorResponse class so that the exception is conveyed to the user in a clear and concise way
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /**
     * The status code of the error response
     */
    private int statusCode;

    /**
     * The response status message
     */
    private String message;
}
