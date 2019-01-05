package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import com.daduo.api.tiktokapi.model.WechatLoginRequest;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    public AuthenticationCodeResponse sendMessageAuthenticationCode(Double number) {
        AuthenticationCodeResponse result = new AuthenticationCodeResponse();
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

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return null;
    }

    public SignUpResponse wechatLogin(WechatLoginRequest wechatLoginRequest) {
        return null;
    }
}
