package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Credit;
import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.model.CreditRequest;
import com.daduo.api.tiktokapi.repository.CreditRepository;
import com.daduo.api.tiktokapi.translator.CreditTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    @Autowired
    private CreditRepository repository;

    @Autowired
    private CreditTranslator translator;

    public CreditData getCreditById(Long userId) {
        Credit credit = repository.findByUserId(userId);
        if (credit == null) {
            credit = addDefaultCredit(userId);
        }
        return translator.translateToCreditData(credit);
    }

    public CreditData addCredit(CreditRequest creditRequest) {
        Credit credit = repository.findByUserId(creditRequest.getUserId());
        if (credit == null) {
            credit = addDefaultCredit(creditRequest.getUserId());
        }
        if (creditRequest.getCredit() != null) {
            credit.setCredit(creditRequest.getCredit());
        }
        if (creditRequest.getPoints() != null) {
            credit.setPoints(creditRequest.getPoints());
        }
        Credit savedCredit = repository.saveAndFlush(credit);
        return translator.translateToCreditData(savedCredit);
    }

    private Credit addDefaultCredit(Long userId) {
        Credit credit = new Credit();
        credit.setCreatedTime(LocalDateTime.now());
        credit.setLastModifiedTime(LocalDateTime.now());
        credit.setCredit(0L);
        credit.setUserId(userId);
        credit.setPoints(0L);
        return repository.save(credit);
    }
}