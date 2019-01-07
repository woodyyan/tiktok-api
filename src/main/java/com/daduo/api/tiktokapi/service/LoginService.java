package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.WechatLoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public LoginResponse login(LoginRequest loginRequest) {
        //TODO 去数据库匹配手机号和密码，找到就登陆成功
        //repository.find(phoneNumber, password)
        return null;
    }

    public LoginResponse wechatLogin(WechatLoginRequest wechatLoginRequest) {
        return null;
    }
}
