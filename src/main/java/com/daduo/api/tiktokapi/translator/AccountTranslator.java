package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.model.AuthData;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import org.joda.time.LocalDateTime;
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

    public Account translateToAccount(Long phoneNumber) {
        Account account = new Account();
        account.setUsername(phoneNumber.toString());
        account.setPhoneNumber(phoneNumber);
        LocalDateTime now = LocalDateTime.now();
        account.setCreatedTime(now);
        account.setLastModifiedTime(now);
        account.setId(System.currentTimeMillis());
        return account;
    }
}
