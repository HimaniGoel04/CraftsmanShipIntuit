package com.craftsmanship.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends APIException {

    public InternalServerErrorException(String message) {
        this(message, "INTERNAL_SERVER_ERROR");
    }

    public InternalServerErrorException(String message, String errorType) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, errorType);
    }

}
