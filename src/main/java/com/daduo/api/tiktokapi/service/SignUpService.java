package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import com.daduo.api.tiktokapi.model.WechatLoginRequest;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    public AuthenticationCodeResponse sendMessageAuthenticationCode(Long number) {
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

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        //TODO 判断验证码正确
        if (signUpRequest.getCode().equals("")) {
            return signUp(signUpRequest.getNumber(), signUpRequest.getPassword(), null);
        } else {
            SignUpResponse response = new SignUpResponse();
            response.setSuccess(false);
            response.setMessage("验证码不正确。");
            return response;
        }
    }

    public SignUpResponse wechatLogin(WechatLoginRequest wechatLoginRequest) {
        return signUp(null, null, wechatLoginRequest.getWechatId());
    }

    private SignUpResponse signUp(Long phoneNumber, String password, String wechatId) {
        Integer id = (int) (System.currentTimeMillis() / 1000);
        Account account = new Account();
        account.setId(id);
        account.setPassword(password);
        account.setPhoneNumber(phoneNumber);
        account.setUsername(null);
        account.setWechatId(wechatId);

        SignUpResponse response = new SignUpResponse();
        try {
//        respository.save(account);
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private void send(Long number) {
        //TODO 发送短信验证码
    }
}
