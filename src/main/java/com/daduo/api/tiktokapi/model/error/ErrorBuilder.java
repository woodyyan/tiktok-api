package com.daduo.api.tiktokapi.model.error;

import com.daduo.api.tiktokapi.exception.ErrorException;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

public final class ErrorBuilder {
    public static Error buildInvalidParameterError(String details) {
        Error error = new Error();
        error.setStatus(String.valueOf(BAD_REQUEST.value()));
        error.setTitle("Invalid Parameter");
        error.setDetails(details);
        return error;
    }

    public static Error buildNotFoundError(String details) {
        Error error = new Error();
        error.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTitle("Not Found");
        error.setDetails(details);
        return error;
    }

    public static ErrorException buildNotFoundErrorException(String message) {
        Error error = buildNotFoundError(message);
        return new ErrorException(OK, error);
    }
}
