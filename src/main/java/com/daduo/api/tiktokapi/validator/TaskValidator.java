package com.daduo.api.tiktokapi.validator;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.TaskRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

@Component
@Slf4j
public class TaskValidator {
    public void validate(TaskRequest taskRequest) {
        List<Error> errorList = new ArrayList<>();
        if (isNullOrEmpty(taskRequest.getUrl())) {
            Error error = new Error();
            error.setTitle("Url不能为空");
            error.setDetails("Url不能为空");
            error.setStatus("400");
            errorList.add(error);
        }
        if (isNullOrEmpty(taskRequest.getName())) {
            Error error = new Error();
            error.setTitle("Name不能为空");
            error.setDetails("Name不能为空");
            error.setStatus("400");
            errorList.add(error);
        }
        if (taskRequest.getItems().size() == 0) {
            Error error = new Error();
            error.setTitle("Items至少要包含一个");
            error.setDetails("Items至少要包含一个");
            error.setStatus("400");
            errorList.add(error);
        }
        if (taskRequest.getCount() < 1) {
            Error error = new Error();
            error.setTitle("次数不能小于1");
            error.setDetails("次数不能小于1");
            error.setStatus("400");
            errorList.add(error);
        }
        if (errorList.size() > 0) {
            Errors errors = new Errors(errorList);
            log.error("TaskRequest is invalid, errors: {}", errors);
            throw new ErrorException(HttpStatus.BAD_REQUEST, errors);
        }
    }
}
