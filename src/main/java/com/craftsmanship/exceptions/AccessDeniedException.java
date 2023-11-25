package com.craftsmanship.exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends APIException {

    public AccessDeniedException(String message) {
        this(message, "ACCESS_DENIED");
    }

    public AccessDeniedException(String message, String errorType) {
        super(HttpStatus.UNAUTHORIZED, message, errorType);
    }

}
