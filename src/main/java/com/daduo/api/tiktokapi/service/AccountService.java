package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AccountData;
import com.daduo.api.tiktokapi.model.AccountRequest;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.daduo.api.tiktokapi.model.error.ErrorBuilder.buildNotFoundErrorException;

@Service
public class AccountService {
    @Autowired
    private AccountTranslator translator;

    @Autowired
    private AccountRepository repository;

    public AccountData updateAccount(AccountRequest accountRequest) throws ErrorException {
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
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }

    public boolean activateAccount(Long userId) {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            Account existingAccount = account.get();
            existingAccount.setStatus(AccountStatus.ACTIVE);
            return true;
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }
}
