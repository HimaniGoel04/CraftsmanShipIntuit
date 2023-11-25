package com.craftsmanship.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
public class APIException extends RuntimeException {

    private final String message;
    private final HttpStatusCode httpStatusCode;
    private final String errorType;
    private final List<String> errorList;

    public APIException(HttpStatusCode httpStatusCode, String message, String errorType) {
        this(httpStatusCode, message, errorType, null);
    }

    public APIException(HttpStatusCode httpStatusCode, String message, String errorType, List<String> errorList) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.errorType = errorType;
        this.errorList = errorList;
    }

}