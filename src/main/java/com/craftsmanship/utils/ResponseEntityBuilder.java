package com.craftsmanship.utils;

import com.craftsmanship.models.APIError;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    private ResponseEntityBuilder() {
    }

    public static ResponseEntity<Object> build(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatusCode());
    }

}