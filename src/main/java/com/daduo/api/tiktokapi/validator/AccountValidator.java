package com.daduo.api.tiktokapi.validator;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountValidator {

    @Autowired
    private AccountRepository accountRepository;

    public void validateUserIdExists(Long userId) {
        boolean exists = accountRepository.existsById(userId);
        if (!exists) {
            throw ErrorBuilder.buildNotFoundErrorException("用户ID找不到。");
        }
    }

    public void validateForbiddenUser(Long ownerId) {
        Optional<Account> account = accountRepository.findById(ownerId);
        if (account.isPresent()) {
            AccountStatus status = account.get().getStatus();
            if (status == AccountStatus.FORBIDDEN) {
                throw ErrorBuilder.buildNotFoundErrorException("你好，已封号，请联系客服激活封号！");
            }
        }
    }
}
