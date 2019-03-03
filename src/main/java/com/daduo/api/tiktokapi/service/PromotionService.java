package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.entity.Promotion;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.repository.PromotionRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import com.daduo.api.tiktokapi.translator.PromotionTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository repository;

    @Autowired
    private PromotionTranslator translator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTranslator accountTranslator;

    public Promotions getPromotions(Long userId) {
        List<Promotion> promotions = repository.findAllByPromotionUserId(userId);
        return translator.toPromotions(promotions);
    }

    public void createPromotion(PromotionRequest promotionRequest) {
        Integer count = repository.countByChildUserId(promotionRequest.getChildUserId());
        if (count > 0) {
            Error error = new Error();
            error.setTitle("已绑定过");
            error.setDetails("不能重复绑定。");
            error.setStatus("400");
            throw new ErrorException(HttpStatus.OK, error);
        }

        Promotion promotion = new Promotion();
        promotion.setChildNickname(accountService.getAccountNickname(promotionRequest.getChildUserId()));
        promotion.setChildUserId(promotionRequest.getChildUserId());
        promotion.setPromotionUserId(promotionRequest.getPromotionUserId());
        promotion.setCreatedTime(LocalDateTime.now());
        repository.save(promotion);
    }

    public AllPromotions getAllPromotions(Pageable page) {
        Page<Promotion> promotions = repository.findAll(page);
        return translator.toAllPromotions(promotions);
    }

    public Accounts getAllChildUsers(Long userId) {
        List<Promotion> promotions = repository.findAllByPromotionUserId(userId);
        List<Long> childUserIds = promotions.stream().map(Promotion::getChildUserId).collect(Collectors.toList());
        List<Account> accounts = accountRepository.findAllById(childUserIds);
        Accounts result = new Accounts();
        for (Account account : accounts) {
            AccountData data = accountTranslator.toAccountData(account);
            result.getData().add(data);
        }
        return result;
    }
}
