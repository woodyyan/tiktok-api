package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResult;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResult;
import com.daduo.api.tiktokapi.model.WechatLoginRequest;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    public AuthenticationCodeResult sendMessageAuthenticationCode(Double number) {
        AuthenticationCodeResult result = new AuthenticationCodeResult();
        try {
            send(number);
            result.setSuccess(true);
            result.setTitle("发送成功");
        } catch (Exception ex) {
            result.setSuccess(false);
            result.setTitle("发送失败");
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    private void send(Double number) {

    }

    public SignUpResult signUp(SignUpRequest signUpRequest) {
        return null;
    }

    public SignUpResult wechatLogin(WechatLoginRequest wechatLoginRequest) {
        return null;
    }
}
