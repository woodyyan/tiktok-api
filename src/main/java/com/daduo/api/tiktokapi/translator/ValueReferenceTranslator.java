package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import com.daduo.api.tiktokapi.model.ValueData;
import com.daduo.api.tiktokapi.model.ValueResponseRequest;
import org.springframework.stereotype.Component;

@Component
public class ValueReferenceTranslator {
    public ValueData translateToValueData(ReferenceValue referenceValue) {
        ValueData data = new ValueData();
        data.setCommissionPercent(referenceValue.getCommissionPercent());
        data.setCreditOfPerRmb(referenceValue.getCreditOfPerRmb());
        data.setPointsOfPerKuaishouComment(referenceValue.getPointsOfPerKuaishouComment());
        data.setPointsOfPerKuaishouFollow(referenceValue.getPointsOfPerKuaishouFollow());
        data.setPointsOfPerKuaishouLike(referenceValue.getPointsOfPerKuaishouLike());
        data.setPointsOfPerKuaishouPlay(referenceValue.getPointsOfPerKuaishouPlay());
        data.setPointsOfPerRmb(referenceValue.getPointsOfPerRmb());
        data.setPointsOfPerTiktokComment(referenceValue.getPointsOfPerTiktokComment());
        data.setPointsOfPerTiktokFollow(referenceValue.getPointsOfPerTiktokFollow());
        data.setPointsOfPerTiktokLike(referenceValue.getPointsOfPerTiktokLike());
        data.setPointsOfPerTiktokPlay(referenceValue.getPointsOfPerTiktokPlay());
        data.setPresentedCreditFor10(referenceValue.getPresentedCreditFor10());
        data.setPresentedCreditFor30(referenceValue.getPresentedCreditFor30());
        data.setPresentedCreditFor50(referenceValue.getPresentedCreditFor50());
        data.setPresentedCreditFor100(referenceValue.getPresentedCreditFor100());
        data.setPresentedCreditFor200(referenceValue.getPresentedCreditFor200());
        data.setPresentedCreditFor500(referenceValue.getPresentedCreditFor500());
        data.setPresentedCreditFor1000(referenceValue.getPresentedCreditFor1000());
        data.setPresentedCreditFor5000(referenceValue.getPresentedCreditFor5000());
        return data;
    }

    public ValueData toValueData(ValueResponseRequest request) {
        return null;
    }
}
