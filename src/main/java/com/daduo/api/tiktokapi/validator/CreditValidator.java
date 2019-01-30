package com.daduo.api.tiktokapi.validator;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.CreditRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class CreditValidator {
    public void validate(CreditRequest creditRequest) {
        if (creditRequest.getUserId() == null || creditRequest.getUserId() < 0) {
            Error error = new Error();
            error.setTitle("Admin ID 不能为空");
            error.setDetails("Admin ID不能为空");
            error.setStatus("400");
            Errors errors = new Errors(Collections.singletonList(error));
            log.error("TaskRequest is invalid, errors: {}", errors);
            throw new ErrorException(HttpStatus.BAD_REQUEST, errors);
        }
    }
}
