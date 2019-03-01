package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Promotion;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.PromotionRequest;
import com.daduo.api.tiktokapi.model.Promotions;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.PromotionRepository;
import com.daduo.api.tiktokapi.translator.PromotionTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository repository;

    @Autowired
    private PromotionTranslator translator;

    @Autowired
    private AccountService accountService;

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
}
