package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.MemberPoints;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.MemberPointsData;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.MemberPointsRepository;
import com.daduo.api.tiktokapi.translator.MemberPointsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemberPointsService {
    @Autowired
    private MemberPointsRepository repository;

    @Autowired
    private MemberPointsTranslator translator;

    public MemberPointsData getPointsById(Long id) {
        MemberPoints points = repository.findByUserId(id);
        if (points != null) {
            return translator.translateToMemberPointsData(points);
        } else {
            Error error = new Error();
            error.setStatus("404");
            error.setDetails("账号找不到，请确认ID是否正确。");
            error.setTitle("账号找不到");
            throw new ErrorException(HttpStatus.NOT_FOUND, error);
        }
    }
}
