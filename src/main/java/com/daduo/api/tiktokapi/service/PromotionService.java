package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Promotion;
import com.daduo.api.tiktokapi.model.Promotions;
import com.daduo.api.tiktokapi.repository.PromotionRepository;
import com.daduo.api.tiktokapi.translator.PromotionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository repository;

    @Autowired
    private PromotionTranslator translator;

    public Promotions getPromotions(Long userId) {
        List<Promotion> promotions = repository.findAllByPromotionUserId(userId);
        return translator.toPromotions(promotions);
    }
}
