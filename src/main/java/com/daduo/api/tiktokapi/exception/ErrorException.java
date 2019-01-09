package com.daduo.api.tiktokapi.exception;

import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.Errors;
import org.springframework.http.HttpStatus;

import static java.util.Collections.singletonList;

public class ErrorException extends RuntimeException {
    private final HttpStatus status;
    private final Errors errors;

    public ErrorException(HttpStatus status, Error error) {
        this(status, new Errors(singletonList(error)));
    }

    public ErrorException(HttpStatus status, Errors errors) {
        this.status = status;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Errors getErrors() {
        return errors;
    }
}
