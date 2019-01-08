package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.model.AuthData;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountTranslator {
    public LoginResponse translateToLoginResponse(Account account) {
        LoginResponse response = new LoginResponse();
        response.setCreatedTime(account.getCreatedTime().toDateTime());
        response.setId(account.getId());
        response.setLastModifiedTime(account.getLastModifiedTime().toDateTime());
        response.setPhoneNumber(account.getPhoneNumber());
        response.setUsername(account.getUsername());
        AuthData authData = new AuthData();
        authData.setAccessToken(account.getAccessToken());
        authData.setExpiresIn(account.getExpiresIn());
        authData.setOpenId(account.getOpenId());
        response.setAuthData(authData);
        return response;
    }

    public SignUpResponse translateToSignUpResponse(Account account) {
        SignUpResponse response = new SignUpResponse();
        response.setCreatedTime(account.getCreatedTime().toDateTime());
        response.setId(account.getId());
        return response;
    }
}
