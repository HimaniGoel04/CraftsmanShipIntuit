package com.craftsmanship.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends APIException {

    public BadRequestException(String message) {
        this(message, "BAD_REQUEST");
    }

    public BadRequestException(String message, String errorType) {
        super(HttpStatus.BAD_REQUEST, message, errorType);
    }

}
