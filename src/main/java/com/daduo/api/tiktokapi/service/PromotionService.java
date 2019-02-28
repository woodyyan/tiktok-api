package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Promotion;
import com.daduo.api.tiktokapi.model.PromotionRequest;
import com.daduo.api.tiktokapi.model.Promotions;
import com.daduo.api.tiktokapi.repository.PromotionRepository;
import com.daduo.api.tiktokapi.translator.PromotionTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
        Promotion promotion = new Promotion();
        promotion.setChildNickname(accountService.getAccountNickname(promotionRequest.getChildUserId()));
        promotion.setChildUserId(promotionRequest.getChildUserId());
        promotion.setPromotionUserId(promotionRequest.getPromotionUserId());
        promotion.setCreatedTime(LocalDateTime.now());
        repository.save(promotion);
    }
}
