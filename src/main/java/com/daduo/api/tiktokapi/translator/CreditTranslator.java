package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Credit;
import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.service.ReferenceValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditTranslator {

    @Autowired
    private ReferenceValueService referenceValueService;

    public CreditData translateToCreditData(Credit credit, String nickname) {
        Integer pointsOfPerRmb = referenceValueService.searchByName("pointsOfPerRmb");

        CreditData data = new CreditData();
        data.setId(credit.getId());
        data.setCredit(credit.getCredit());
        data.setPoints(credit.getPoints());
        data.setMoney(credit.getPoints() / pointsOfPerRmb);
        data.setUserId(credit.getUserId());
        data.setNickname(nickname);
        data.setCreatedTime(credit.getCreatedTime().toDateTime());
        data.setLastModifiedTime(credit.getLastModifiedTime().toDateTime());
        return data;
    }
}
