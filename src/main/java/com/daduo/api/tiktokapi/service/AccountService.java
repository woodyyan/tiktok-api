package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AccountData;
import com.daduo.api.tiktokapi.model.AccountRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountTranslator translator;

    @Autowired
    private AccountRepository repository;

    public AccountData updateAccount(AccountRequest accountRequest) {
        Optional<Account> account = repository.findById(accountRequest.getData().getId());
        if (account.isPresent()) {
            Account existingAccount = account.get();
            existingAccount.setLastModifiedTime(LocalDateTime.now());
            existingAccount.setUsername(accountRequest.getData().getUsername());
            existingAccount.setQq(accountRequest.getData().getQq());
            existingAccount.setWechat(accountRequest.getData().getWechat());
            existingAccount.setAvatar(accountRequest.getData().getAvatar());
            existingAccount.setAddress(accountRequest.getData().getAddress());
            Account savedAccount = repository.saveAndFlush(existingAccount);
            return translator.translateToAccountData(savedAccount);
        } else {
            Error error = new Error();
            error.setStatus("404");
            error.setDetails("账号找不到，请确认ID是否正确。");
            error.setTitle("账号找不到");
            throw new ErrorException(HttpStatus.NOT_FOUND, error);
        }
    }
}
