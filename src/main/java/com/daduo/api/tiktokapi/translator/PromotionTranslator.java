package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.entity.Promotion;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PromotionTranslator {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotions toPromotions(List<Promotion> promotions) {
        Promotions result = new Promotions();
        List<PromotionData> data = new ArrayList<>();
        for (Promotion promotion : promotions) {
            PromotionData promotionData = new PromotionData();
            promotionData.setChildNickname(promotion.getChildNickname());
            promotionData.setChildUserId(promotion.getChildUserId());
            promotionData.setCreatedTime(promotion.getCreatedTime().toDateTime());
            promotionData.setId(promotion.getId());
            promotionData.setPromotionUserId(promotion.getPromotionUserId());
            data.add(promotionData);
        }
        result.setData(data);
        return result;
    }

    public AllPromotions toAllPromotions(Page<Promotion> promotions) {
        AllPromotions allPromotions = new AllPromotions();
        List<PromotionInfoData> data = new ArrayList<>();
        for (Promotion promotion : promotions) {
            Optional<Account> account = accountRepository.findById(promotion.getPromotionUserId());
            if (account.isPresent()) {
                PromotionInfoData promotionInfo = new PromotionInfoData();
                promotionInfo.setUserId(promotion.getPromotionUserId());
                promotionInfo.setUserNickname(account.get().getNickname());
                promotionInfo.setRegisterTime(account.get().getCreatedTime().toDateTime());
                List<Promotion> allByPromotionUserId = promotionRepository.findAllByPromotionUserId(promotion.getPromotionUserId());
                promotionInfo.setPromotionCount(allByPromotionUserId.size());
                data.add(promotionInfo);
            }
        }
        allPromotions.setData(data);
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(promotions.getNumber());
        meta.setPageSize(promotions.getSize());
        meta.setTotalElements(promotions.getTotalElements());
        meta.setTotalPages(promotions.getTotalPages());
        allPromotions.setMeta(meta);
        return allPromotions;
    }
}
