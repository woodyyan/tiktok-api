package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.model.CreditResponse;
import com.daduo.api.tiktokapi.service.CreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/credit")
@RestController
@Slf4j
@Api(tags = "充值币接口", description = "充值币管理")
public class CreditController {
    @Autowired
    private CreditService service;

    @GetMapping("/{userId}")
    @ApiOperation("获取当前ID的充值币")
    public CreditResponse getCreditByUserId(Long userId) {
        log.info("[START] Get credit with userId: {}", userId);
        CreditData data = service.getCreditById(userId);
        CreditResponse response = new CreditResponse();
        response.setData(data);
        log.info("[END] Get credit with response: {}", response);
        return response;
    }
}
