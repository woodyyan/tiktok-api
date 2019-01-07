package com.daduo.api.tiktokapi.exception;

import org.springframework.http.HttpStatus;
import com.daduo.api.tiktokapi.model.error.Error;

public class ErrorException extends RuntimeException {
    private final HttpStatus status;
    private final Error error;

    public ErrorException(HttpStatus status, Error error) {
        this.status = status;
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Error getError() {
        return error;
    }
}
