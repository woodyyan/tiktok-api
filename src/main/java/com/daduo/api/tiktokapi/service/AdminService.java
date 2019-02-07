package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AdminLoginRequest;
import com.daduo.api.tiktokapi.model.AdminRequest;
import com.daduo.api.tiktokapi.model.AdminResponse;
import com.daduo.api.tiktokapi.model.ResetAdminPasswordRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.AdminRepository;
import com.daduo.api.tiktokapi.translator.AdminTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    @Autowired
    private AdminTranslator translator;

    public AdminResponse login(AdminLoginRequest loginRequest) {
        Admin admin = repository.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (admin != null) {
            if (admin.getPassword().equals(loginRequest.getPassword())) {
                return translator.toResponse(admin);
            } else {
                Error error = new Error();
                error.setStatus(String.valueOf(BAD_REQUEST.value()));
                error.setTitle("密码错误");
                error.setDetails("密码错误");
                throw new ErrorException(HttpStatus.BAD_REQUEST, error);
            }
        }
        throw ErrorBuilder.buildNotFoundErrorException("用户找不到");
    }

    public AdminResponse addAdminUser(AdminRequest request) {
        Admin admin = translator.toAdmin(request);
        Admin savedAdmin = repository.save(admin);
        return translator.toResponse(savedAdmin);
    }

    public void resetPassword(ResetAdminPasswordRequest request) {
        Admin admin = repository.findByPhoneNumber(request.getPhoneNumber());
        if (admin != null) {
            //TODO 验证 code验证码
            if (validateCode(request.getCode())) {
                admin.setPassword(request.getPassword());
                repository.saveAndFlush(admin);
            } else {
                Error error = ErrorBuilder.buildInvalidParameterError("验证码错误");
                throw new ErrorException(HttpStatus.BAD_REQUEST, error);
            }
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("用户不存在");
        }
    }

    private boolean validateCode(Integer code) {
        return false;
    }
}
