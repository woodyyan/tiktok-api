package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.model.*;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AccountTranslator {
    public LoginResponse translateToLoginResponse(Account account, String token) {
        LoginResponse response = new LoginResponse();
        response.setCreatedTime(account.getCreatedTime().toDateTime());
        response.setId(account.getId());
        response.setLastModifiedTime(account.getLastModifiedTime().toDateTime());
        response.setPhoneNumber(account.getPhoneNumber());
        response.setSessionToken(token);
        response.setUsername(account.getUsername());
        AuthData authData = new AuthData();
        authData.setAccessToken(account.getAccessToken());
        authData.setExpiresIn(account.getExpiresIn());
        authData.setOpenId(account.getOpenId());
        response.setAuthData(authData);

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
        account.setStatus(AccountStatus.INACTIVE);
        return account;
    }

    public AccountData translateToAccountData(Account savedAccount) {
        AccountData data = new AccountData();
        data.setAddress(savedAccount.getAddress());
        data.setAvatar(savedAccount.getAvatar());
        data.setCreatedTime(savedAccount.getCreatedTime().toDateTime());
        data.setId(savedAccount.getId());
        data.setLastModifiedTime(savedAccount.getLastModifiedTime().toDateTime());
        data.setPhoneNumber(savedAccount.getPhoneNumber());
        data.setQq(savedAccount.getQq());
        data.setUsername(savedAccount.getUsername());
        data.setWechat(savedAccount.getWechat());
        data.setStatus(savedAccount.getStatus());
        return data;
    }

    public Accounts toAccounts(Page<Account> pagedAccounts) {
        Accounts accounts = new Accounts();
        for (Account account : pagedAccounts.getContent()) {
            accounts.getData().add(translateToAccountData(account));
        }
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(pagedAccounts.getNumber());
        meta.setPageSize(pagedAccounts.getSize());
        meta.setTotalElements(pagedAccounts.getTotalElements());
        meta.setTotalPages(pagedAccounts.getTotalPages());
        accounts.setMeta(meta);
        return accounts;
    }
}
