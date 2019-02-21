package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import com.daduo.api.tiktokapi.model.ValueData;
import com.daduo.api.tiktokapi.model.ValueResponseRequest;
import com.daduo.api.tiktokapi.repository.ValueReferenceRepository;
import com.daduo.api.tiktokapi.translator.ValueReferenceTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceValueService {
    @Autowired
    private ValueReferenceRepository repository;

    @Autowired
    private ValueReferenceTranslator translator;

    public ValueData getReferenceValues() {
        List<ReferenceValue> all = repository.findAll();
        if (all.size() == 0) {
            getDefaultValue(all);
        }
        return translator.translateToValueData(all.get(0));
    }

    public ValueData modifyReferenceValues(ValueResponseRequest request) {
        List<ReferenceValue> all = repository.findAll();
        if (all.size() == 0) {
            getDefaultValue(all);
        }
        ReferenceValue referenceValue = all.get(0);
        modifyValues(request, referenceValue);
        ReferenceValue savedValue = repository.saveAndFlush(referenceValue);
        return translator.translateToValueData(savedValue);
    }

    private void modifyValues(ValueResponseRequest request, ReferenceValue referenceValue) {
        if (request.getCommissionPercent() != null) {
            referenceValue.setCommissionPercent(request.getCommissionPercent());
        }
        if (request.getCreditOfPerRmb() != null) {
            referenceValue.setCreditOfPerRmb(request.getCreditOfPerRmb());
        }
        if (request.getPointsOfPerKuaishouComment() != null) {
            referenceValue.setPointsOfPerKuaishouComment(request.getPointsOfPerKuaishouComment());
        }
        if (request.getPointsOfPerKuaishouFollow() != null) {
            referenceValue.setPointsOfPerKuaishouFollow(request.getPointsOfPerKuaishouFollow());
        }
        if (request.getPointsOfPerKuaishouLike() != null) {
            referenceValue.setPointsOfPerKuaishouLike(request.getPointsOfPerKuaishouLike());
        }
        if (request.getPointsOfPerKuaishouPlay() != null) {
            referenceValue.setPointsOfPerKuaishouPlay(request.getPointsOfPerKuaishouPlay());
        }
        if (request.getPointsOfPerRmb() != null) {
            referenceValue.setPointsOfPerRmb(request.getPointsOfPerRmb());
        }
        if (request.getPointsOfPerTiktokComment() != null) {
            referenceValue.setPointsOfPerTiktokComment(request.getPointsOfPerTiktokComment());
        }
        if (request.getPointsOfPerTiktokFollow() != null) {
            referenceValue.setPointsOfPerTiktokFollow(request.getPointsOfPerTiktokFollow());
        }
        if (request.getPointsOfPerTiktokLike() != null) {
            referenceValue.setPointsOfPerTiktokLike(request.getPointsOfPerTiktokLike());
        }
        if (request.getPointsOfPerTiktokPlay() != null) {
            referenceValue.setPointsOfPerTiktokPlay(request.getPointsOfPerTiktokPlay());
        }
        if (request.getPresentedCreditFor10() != null) {
            referenceValue.setPresentedCreditFor10(request.getPresentedCreditFor10());
        }
        if (request.getPresentedCreditFor30() != null) {
            referenceValue.setPresentedCreditFor30(request.getPresentedCreditFor30());
        }
        if (request.getPresentedCreditFor50() != null) {
            referenceValue.setPresentedCreditFor50(request.getPresentedCreditFor50());
        }
        if (request.getPresentedCreditFor100() != null) {
            referenceValue.setPresentedCreditFor100(request.getPresentedCreditFor100());
        }
        if (request.getPresentedCreditFor200() != null) {
            referenceValue.setPresentedCreditFor200(request.getPresentedCreditFor200());
        }
        if (request.getPresentedCreditFor500() != null) {
            referenceValue.setPresentedCreditFor500(request.getPresentedCreditFor500());
        }
        if (request.getPresentedCreditFor1000() != null) {
            referenceValue.setPresentedCreditFor1000(request.getPresentedCreditFor1000());
        }
        if (request.getPresentedCreditFor5000() != null) {
            referenceValue.setPresentedCreditFor5000(request.getPresentedCreditFor5000());
        }
    }

    private void getDefaultValue(List<ReferenceValue> all) {
        ReferenceValue value = new ReferenceValue();
        value.setCommissionPercent(40);
        value.setCreditOfPerRmb(100);
        value.setPointsOfPerKuaishouComment(100);
        value.setPointsOfPerKuaishouFollow(100);
        value.setPointsOfPerKuaishouLike(100);
        value.setPointsOfPerKuaishouPlay(100);
        value.setPointsOfPerRmb(100);
        value.setPointsOfPerTiktokComment(100);
        value.setPointsOfPerTiktokFollow(100);
        value.setPointsOfPerTiktokLike(100);
        value.setPointsOfPerTiktokPlay(100);
        value.setPresentedCreditFor10(100);
        value.setPresentedCreditFor30(100);
        value.setPresentedCreditFor50(100);
        value.setPresentedCreditFor100(100);
        value.setPresentedCreditFor200(100);
        value.setPresentedCreditFor500(100);
        value.setPresentedCreditFor1000(100);
        value.setPresentedCreditFor5000(100);
        all.add(value);
    }
}
