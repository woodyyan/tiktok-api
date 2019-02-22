package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.ValueData;
import com.daduo.api.tiktokapi.model.ValueResponseRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.ValueReferenceRepository;
import com.daduo.api.tiktokapi.translator.ValueReferenceTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReferenceValueService {
    @Autowired
    private ValueReferenceRepository repository;

    @Autowired
    private ValueReferenceTranslator translator;

    public ValueData getReferenceValues() {
        ReferenceValue referenceValue = getReferenceValue();
        return translator.translateToValueData(referenceValue);
    }

    public ValueData modifyReferenceValues(ValueResponseRequest request) {
        ReferenceValue referenceValue = getReferenceValue();
        modifyValues(request, referenceValue);
        ReferenceValue savedValue = repository.saveAndFlush(referenceValue);
        return translator.translateToValueData(savedValue);
    }

    public Double searchReferenceValue(String name) {
        ReferenceValue referenceValue = getReferenceValue();
        return searchByName(name, referenceValue);
    }

    private ReferenceValue getReferenceValue() {
        List<ReferenceValue> all = repository.findAll();
        if (all.size() == 0) {
            getDefaultValue(all);
        }
        return all.get(0);
    }

    private Double searchByName(String name, ReferenceValue referenceValue) {
        if (Objects.equals(name, "creditOfPerRmb")) {
            return referenceValue.getCreditOfPerRmb();
        } else if (Objects.equals(name, "pointsOfPerKuaishouComment")) {
            return referenceValue.getPointsOfPerKuaishouComment();
        } else if (Objects.equals(name, "pointsOfPerKuaishouFollow")) {
            return referenceValue.getPointsOfPerKuaishouFollow();
        } else if (Objects.equals(name, "pointsOfPerKuaishouLike")) {
            return referenceValue.getPointsOfPerKuaishouLike();
        } else if (Objects.equals(name, "pointsOfPerKuaishouLikeAndFollow")) {
            return referenceValue.getPointsOfPerKuaishouLikeAndFollow();
        } else if (Objects.equals(name, "pointsOfPerKuaishouLikeAndFollowAndComment")) {
            return referenceValue.getPointsOfPerKuaishouLikeAndFollowAndComment();
        } else if (Objects.equals(name, "pointsOfPerKuaishouPlay")) {
            return referenceValue.getPointsOfPerKuaishouPlay();
        } else if (Objects.equals(name, "pointsOfPerRmb")) {
            return referenceValue.getPointsOfPerRmb();
        } else if (Objects.equals(name, "pointsOfPerTiktokComment")) {
            return referenceValue.getPointsOfPerTiktokComment();
        } else if (Objects.equals(name, "pointsOfPerTiktokFollow")) {
            return referenceValue.getPointsOfPerTiktokFollow();
        } else if (Objects.equals(name, "pointsOfPerTiktokLike")) {
            return referenceValue.getPointsOfPerTiktokLike();
        } else if (Objects.equals(name, "pointsOfPerTiktokLikeAndFollow")) {
            return referenceValue.getPointsOfPerTiktokLikeAndFollow();
        } else if (Objects.equals(name, "pointsOfPerTiktokLikeAndFollowAndComment")) {
            return referenceValue.getPointsOfPerTiktokLikeAndFollowAndComment();
        } else if (Objects.equals(name, "pointsOfPerTiktokPlay")) {
            return referenceValue.getPointsOfPerTiktokPlay();
        } else if (Objects.equals(name, "presentedCreditFor10")) {
            return referenceValue.getPresentedCreditFor10();
        } else if (Objects.equals(name, "presentedCreditFor30")) {
            return referenceValue.getPresentedCreditFor30();
        } else if (Objects.equals(name, "presentedCreditFor50")) {
            return referenceValue.getPresentedCreditFor50();
        } else if (Objects.equals(name, "presentedCreditFor100")) {
            return referenceValue.getPresentedCreditFor100();
        } else if (Objects.equals(name, "presentedCreditFor200")) {
            return referenceValue.getPresentedCreditFor200();
        } else if (Objects.equals(name, "presentedCreditFor500")) {
            return referenceValue.getPresentedCreditFor500();
        } else if (Objects.equals(name, "presentedCreditFor1000")) {
            return referenceValue.getPresentedCreditFor1000();
        } else if (Objects.equals(name, "presentedCreditFor5000")) {
            return referenceValue.getPresentedCreditFor5000();
        } else if (Objects.equals(name, "commissionPercent")) {
            return (double) referenceValue.getCommissionPercent();
        }
        Error error = ErrorBuilder.buildInvalidParameterError("参数名找不到。");
        throw new ErrorException(HttpStatus.BAD_REQUEST, error);
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
        if (request.getPointsOfPerKuaishouLikeAndFollow() != null) {
            referenceValue.setPointsOfPerKuaishouLikeAndFollow(request.getPointsOfPerKuaishouLikeAndFollow());
        }
        if (request.getPointsOfPerKuaishouLikeAndFollowAndComment() != null) {
            referenceValue.setPointsOfPerKuaishouLikeAndFollowAndComment(request.getPointsOfPerKuaishouLikeAndFollowAndComment());
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
        if (request.getPointsOfPerTiktokLikeAndFollow() != null) {
            referenceValue.setPointsOfPerTiktokLikeAndFollow(request.getPointsOfPerTiktokLikeAndFollow());
        }
        if (request.getPointsOfPerTiktokLikeAndFollowAndComment() != null) {
            referenceValue.setPointsOfPerTiktokLikeAndFollowAndComment(request.getPointsOfPerTiktokLikeAndFollowAndComment());
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
        value.setPointsOfPerKuaishouLikeAndFollow(200);
        value.setPointsOfPerKuaishouLikeAndFollowAndComment(300);
        value.setPointsOfPerTiktokLikeAndFollow(200);
        value.setPointsOfPerTiktokLikeAndFollowAndComment(300);
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
