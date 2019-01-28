package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import com.daduo.api.tiktokapi.model.ValueData;
import org.springframework.stereotype.Component;

@Component
public class ValueReferenceTranslator {
    public ValueData translateToValueData(ReferenceValue referenceValue) {
        ValueData data = new ValueData();
        data.setCredit(referenceValue.getCredit());
        data.setPoints(referenceValue.getPoints());
        data.setRmb(referenceValue.getRmb());
        return data;
    }
}
