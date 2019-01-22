package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.MemberPointsData;
import com.daduo.api.tiktokapi.model.PointsResponse;
import com.daduo.api.tiktokapi.service.MemberPointsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/points")
@RestController
@Slf4j
@Api(tags = "积分接口", description = "积分管理")
public class MemberPointsController {

    @Autowired
    private MemberPointsService service;

    @GetMapping("/{userId}")
    @ApiOperation("获取当前ID的积分")
    public PointsResponse getPointsByUserId(Long userId) {
        log.info("[START] Get points with userId: {}", userId);
        MemberPointsData data = service.getPointsById(userId);
        PointsResponse response = new PointsResponse();
        response.setData(data);
        log.info("[END] Get points with response: {}", response);
        return response;
    }
}
