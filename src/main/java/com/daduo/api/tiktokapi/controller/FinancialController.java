package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.FinancialInfo;
import com.daduo.api.tiktokapi.model.UserFinancialInfoResponse;
import com.daduo.api.tiktokapi.service.FinancialService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/financial")
@RestController
@Slf4j
@Api(tags = "营收情况接口", description = "营收情况统计数据")
public class FinancialController {

    @Autowired
    private FinancialService service;

    @GetMapping
    public FinancialInfo getAllFinancialInfo() {
        log.info("[START] Get all financial info.");
        FinancialInfo info = service.getAllFinancialInfo();
        log.info("[END] Get all financial info with {}.", info);
        return info;
    }

    @GetMapping("/user")
    public UserFinancialInfoResponse getAllUserFinancialInfo() {
        log.info("[START] Get all financial info by user.");
        UserFinancialInfoResponse info = service.getAllUserFinancialInfo();
        log.info("[END] Get all user financial info with {}.", info);
        return info;
    }
}
