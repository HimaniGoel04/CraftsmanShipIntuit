package com.craftsmanship.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends APIException {

    public BadRequestException(String message) {
        this(message, "BAD_REQUEST");
    }

    public BadRequestException(String message, String errorType) {
        super(HttpStatus.BAD_REQUEST, message, errorType);
    }

    public BadRequestException(String message, List<String> errorList) {
        super(HttpStatus.BAD_REQUEST, message, "BAD_REQUEST", errorList);
    }

    public BadRequestException(String message, String errorType, List<String> errorList) {
        super(HttpStatus.BAD_REQUEST, message, errorType, errorList);
    }

}
