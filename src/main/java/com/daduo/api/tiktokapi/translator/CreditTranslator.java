package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Credit;
import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.model.CreditRequest;
import org.springframework.stereotype.Component;

@Component
public class CreditTranslator {
    public CreditData translateToCreditData(Credit credit) {
        CreditData data = new CreditData();
        data.setId(credit.getId());
        data.setCredit(credit.getCredit());
        data.setPoints(credit.getPoints());
        data.setUserId(credit.getUserId());
        data.setCreatedTime(credit.getCreatedTime().toDateTime());
        data.setLastModifiedTime(credit.getLastModifiedTime().toDateTime());
        return data;
    }

    public Credit translateToCredit(CreditRequest creditRequest) {
        return null;
    }
}
