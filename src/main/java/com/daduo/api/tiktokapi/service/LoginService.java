package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.WechatLoginRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AccountRepository repository;

    public LoginResponse login(LoginRequest loginRequest) {
        //TODO 去数据库匹配手机号和密码，找到就登陆成功
        Account account = repository.findOneByPhoneNumberAndPassword(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        if (account != null) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setMessage("登陆成功");
            return response;
        }

        Error error = new Error();
        error.setStatus("404");
        error.setTitle("手机号或密码不正确");
        error.setDetails("手机号或密码不正确, 请重试。");
        throw new ErrorException(HttpStatus.NOT_FOUND, error);
    }

    public LoginResponse wechatLogin(WechatLoginRequest wechatLoginRequest) {
        return null;
    }
}
