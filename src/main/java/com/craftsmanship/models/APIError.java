package com.craftsmanship.models;

import com.craftsmanship.exceptions.APIException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class APIError {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final HttpStatusCode httpStatusCode;
    private final String message;
    private final String errorType;
    private List<String> errorList;

    public APIError(APIException apiException) {
        this.httpStatusCode = apiException.getHttpStatusCode();
        this.message = apiException.getMessage();
        this.errorType = apiException.getErrorType();
        this.errorList = apiException.getErrorList();
    }

    public APIError(HttpStatusCode httpStatusCode, String message, String errorType) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.errorType = errorType;
    }
}