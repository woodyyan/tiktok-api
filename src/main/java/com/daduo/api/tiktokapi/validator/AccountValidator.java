package com.daduo.api.tiktokapi.validator;

import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}