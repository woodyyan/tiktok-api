package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Credit;
import com.daduo.api.tiktokapi.entity.CreditOrder;
import com.daduo.api.tiktokapi.model.AccountData;
import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.model.CreditRequest;
import com.daduo.api.tiktokapi.repository.CreditOrderRepository;
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

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditOrderRepository creditOrderRepository;

    public CreditData getCreditById(Long userId) {
        Credit credit = repository.findByUserId(userId);
        if (credit == null) {
            credit = addDefaultCredit(userId);
        }
        String nickname = accountService.getAccountNickname(userId);
        return translator.translateToCreditData(credit, nickname);
    }

    public CreditData modifyCredit(CreditRequest creditRequest) {
        Credit credit = repository.findByUserId(creditRequest.getUserId());
        if (credit == null) {
            credit = addDefaultCredit(creditRequest.getUserId());
        }
        if (creditRequest.getCredit() != null) {
            credit.setCredit(credit.getCredit() + creditRequest.getCredit());
        }
        if (creditRequest.getPoints() != null) {
            credit.setPoints(credit.getPoints() + creditRequest.getPoints());
        }
        saveCreditOrder(creditRequest);
        Credit savedCredit = repository.saveAndFlush(credit);
        AccountData account = accountService.getAccount(creditRequest.getUserId());
        return translator.translateToCreditData(savedCredit, account.getNickname());
    }

    private void saveCreditOrder(CreditRequest creditRequest) {
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setUserId(creditRequest.getUserId());
        creditOrder.setCredit(creditRequest.getCredit());
        creditOrder.setPoints(creditRequest.getPoints());
        creditOrder.setCreatedTime(LocalDateTime.now());
        creditOrder.setLastModifiedTime(LocalDateTime.now());
        creditOrderRepository.save(creditOrder);
    }

    private Credit addDefaultCredit(Long userId) {
        Credit credit = new Credit();
        credit.setCreatedTime(LocalDateTime.now());
        credit.setLastModifiedTime(LocalDateTime.now());
        credit.setCredit(0);
        credit.setUserId(userId);
        credit.setPoints(0);
        return repository.save(credit);
    }
}
