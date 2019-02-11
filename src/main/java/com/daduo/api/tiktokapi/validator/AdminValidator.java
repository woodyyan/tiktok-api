package com.daduo.api.tiktokapi.validator;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {
    @Autowired
    private AdminRepository repository;

    public void validateExists(Long phoneNumber) {
        Admin admin = repository.findByPhoneNumber(phoneNumber);
        if (admin != null) {
            throwNotFound();
        }
    }

    private void throwNotFound() {
        Error error = new Error();
        error.setTitle("用户已存在");
        error.setDetails("手机号已注册");
        error.setStatus("400");
        throw new ErrorException(HttpStatus.BAD_REQUEST, error);
    }
}
