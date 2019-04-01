package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.service.FinancialService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public MainDataDetail getMainDataDetail(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                            @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                            @ApiParam(value = "分页")
                                                    Pageable page) {
        log.info("[START] Get main data detail.");
        MainDataDetail detail = service.getMainDataDetail(startDate, endDate, page);
        log.info("[END] Get main data detail with {}.", detail);
        return detail;
    }

    @GetMapping("/main/{userId}")
    @ApiOperation(value = "根据会员ID获取主要数据明细（后台）")
    public MainDataDetailData searchMainDataDetail(@PathVariable @ApiParam("会员ID") String userId) {
        log.info("[START] Get main data detail.");
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        MainDataDetailData detail = service.getMainDataDetailByUserId(value);
        log.info("[END] Get main data detail with {}.", detail);
        return detail;
    }

    @GetMapping("/other")
    @ApiOperation(value = "获取其他数据明细（后台）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public OtherDataDetail getOtherDataDetail(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                              @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                              @ApiParam(value = "分页")
                                                      Pageable page) {
        log.info("[START] Get other data detail.");
        OtherDataDetail detail = service.getOtherDataDetail(startDate, endDate, page);
        log.info("[END] Get other data detail with {}.", detail);
        return detail;
    }

    @GetMapping("/other/{userId}")
    @ApiOperation(value = "根据会员ID获取其他数据明细（后台）")
    public OtherDataDetailData getOtherDataDetailByUserId(@PathVariable @ApiParam("会员ID") String userId) {
        log.info("[START] Get other data detail by userId {}.", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        OtherDataDetailData detail = service.getOtherDataDetailByUserId(value);
        log.info("[END] Get other data detail by userId with {}.", detail);
        return detail;
    }
}
