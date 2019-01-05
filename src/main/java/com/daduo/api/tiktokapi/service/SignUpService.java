package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResult;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    public AuthenticationCodeResult sendMessageAuthenticationCode(Integer number) {
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

    private void send(Integer number) {

    }
}
