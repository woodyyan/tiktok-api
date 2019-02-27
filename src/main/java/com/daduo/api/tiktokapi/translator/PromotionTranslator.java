package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Promotion;
import com.daduo.api.tiktokapi.model.PromotionData;
import com.daduo.api.tiktokapi.model.Promotions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromotionTranslator {
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
}
