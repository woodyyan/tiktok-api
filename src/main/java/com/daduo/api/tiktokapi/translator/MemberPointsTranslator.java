package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.MemberPoints;
import com.daduo.api.tiktokapi.model.MemberPointsData;
import org.springframework.stereotype.Component;

@Component
public class MemberPointsTranslator {
    public MemberPointsData translateToMemberPointsData(MemberPoints points) {
        MemberPointsData data = new MemberPointsData();
        data.setCreatedTime(points.getCreatedTime().toDateTime());
        data.setLastModifiedTime(points.getLastModifiedTime().toDateTime());
        data.setId(points.getId());
        data.setUserId(points.getUserId());
        data.setPoints(points.getPoints());
        return data;
    }
}
