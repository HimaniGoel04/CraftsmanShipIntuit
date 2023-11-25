package com.craftsmanship.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends APIException {

    public ResourceNotFoundException(String message) {
        this(message, "NOT_FOUND");
    }

    public ResourceNotFoundException(String message, String errorType) {
        super(HttpStatus.NOT_FOUND, message, errorType);
    }

}
