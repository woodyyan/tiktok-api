package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import com.daduo.api.tiktokapi.model.ValueData;
import com.daduo.api.tiktokapi.repository.ValueReferenceRepository;
import com.daduo.api.tiktokapi.translator.ValueReferenceTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueReferenceService {
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
        value.setCredit(1L);
        value.setPoints(1L);
        value.setRmb(1L);
        all.add(value);
    }
}
