package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Credit;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.CreditRepository;
import com.daduo.api.tiktokapi.translator.CreditTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    @Autowired
    private CreditRepository repository;

    @Autowired
    private CreditTranslator translator;

    public CreditData getCreditById(Long userId) {
        Credit credit = repository.findByUserId(userId);
        //TODO
        if (credit == null) {
            credit = new Credit();
            credit.setCreatedTime(LocalDateTime.now());
            credit.setCredit(123L);
            credit.setUserId(111L);
            credit.setId(1L);
            credit.setLastModifiedTime(LocalDateTime.now());
        }

        if (credit != null) {
            return translator.translateToCreditData(credit);
        }
        Error error = new Error();
        error.setStatus("404");
        error.setDetails("账号找不到，请确认ID是否正确。");
        error.setTitle("账号找不到");
        throw new ErrorException(HttpStatus.NOT_FOUND, error);
    }
}
