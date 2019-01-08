package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.PlatformLoginRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class LoginService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountTranslator translator;

    public LoginResponse login(LoginRequest loginRequest) {
        Account account = repository.findOneByPhoneNumberAndPassword(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        if (account != null) {
            return translator.translateToLoginResponse(account);
        }

        Error error = new Error();
        error.setStatus("404");
        error.setTitle("手机号或密码不正确");
        error.setDetails("手机号或密码不正确, 请重试。");
        throw new ErrorException(HttpStatus.NOT_FOUND, error);
    }

    public LoginResponse platformLogin(PlatformLoginRequest platformLoginRequest) {
        return null;
    }
}
