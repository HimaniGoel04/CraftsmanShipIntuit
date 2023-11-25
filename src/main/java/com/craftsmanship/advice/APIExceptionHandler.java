package com.craftsmanship.advice;

import com.craftsmanship.exceptions.APIException;
import com.craftsmanship.models.APIError;
import com.craftsmanship.utils.ResponseEntityBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({APIException.class})
    public ResponseEntity<Object> handleAll(APIException apiException) {
        apiException.printStackTrace();
        log.error(apiException.getMessage());
        APIError apiError = new APIError(apiException);
        return ResponseEntityBuilder.build(apiError);

    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), "INTERNAL_SERVER_ERROR");
        return ResponseEntityBuilder.build(apiError);

    }

}