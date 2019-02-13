package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import com.daduo.api.tiktokapi.model.ValueData;
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
