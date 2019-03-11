package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.FinancialInfo;
import com.daduo.api.tiktokapi.model.MainDataDetail;
import com.daduo.api.tiktokapi.model.OtherDataDetail;
import com.daduo.api.tiktokapi.model.UserFinancialInfoResponse;
import com.daduo.api.tiktokapi.service.FinancialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "获取整体营收情况（后台）")
    public FinancialInfo getAllFinancialInfo() {
        log.info("[START] Get all financial info.");
        FinancialInfo info = service.getAllFinancialInfo();
        log.info("[END] Get all financial info with {}.", info);
        return info;
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取会员财务情况（后台）")
    public UserFinancialInfoResponse getAllUserFinancialInfo() {
        log.info("[START] Get all financial info by user.");
        UserFinancialInfoResponse info = service.getAllUserFinancialInfo();
        log.info("[END] Get all user financial info with {}.", info);
        return info;
    }

    @GetMapping("/main")
    @ApiOperation(value = "获取主要数据明细（后台）")
    public MainDataDetail getMainDataDetail() {
        log.info("[START] Get main data detail.");
        MainDataDetail detail = service.getMainDataDetail();
        log.info("[END] Get main data detail with {}.", detail);
        return detail;
    }

    @GetMapping("/other")
    @ApiOperation(value = "获取其他数据明细（后台）")
    public OtherDataDetail getOtherDataDetail() {
        log.info("[START] Get other data detail.");
        OtherDataDetail detail = service.getOtherDataDetail();
        log.info("[END] Get other data detail with {}.", detail);
        return detail;
    }
}
