package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.error.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.daduo.api.tiktokapi")
@Slf4j
public class BasicControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Errors> handleException(ErrorException e) {
        log.error("Error exception: ", e);
        return new ResponseEntity<>(e.getErrors(), e.getStatus());
    }
}
