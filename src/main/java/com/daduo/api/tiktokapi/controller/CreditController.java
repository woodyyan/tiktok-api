package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.service.CreditService;
import com.daduo.api.tiktokapi.validator.AccountValidator;
import com.daduo.api.tiktokapi.validator.CreditValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member/credit")
@RestController
@Slf4j
@Api(tags = "充值币积分接口", description = "充值币积分管理")
public class CreditController {
    @Autowired
    private CreditService service;

    @Autowired
    private CreditValidator validator;

    @Autowired
    private AccountValidator accountValidator;

    @GetMapping("/{userId}")
    @ApiOperation(value = "获取当前ID的充值币积分", notes = "money是可兑换金额")
    public CreditResponse getCreditByUserId(@PathVariable @ApiParam("用户ID") String userId) {
        log.info("[START] Get credit with userId: {}", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        accountValidator.validateUserIdExists(value);
        CreditData data = service.getCreditById(value);
        CreditResponse response = new CreditResponse();
        response.setData(data);
        log.info("[END] Get credit with response: {}", response);
        return response;
    }

    @PostMapping
    @ApiOperation(value = "充值或者添加积分", notes = "扣除充值币积分就传负数")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditResponse modifyCredit(@RequestBody @ApiParam(value = "充值币Json") CreditRequest creditRequest) {
        log.info("[START] Add credit with request: {}", creditRequest);
        accountValidator.validateUserIdExists(creditRequest.getUserId());
        validator.validate(creditRequest);
        CreditData creditData = service.modifyCredit(creditRequest);
        CreditResponse response = new CreditResponse();
        response.setData(creditData);
        log.info("[END] Add credit with response: {}", response);
        return response;
    }

    @GetMapping("/order/{userId}")
    @ApiOperation(value = "获取充值明细", notes = "获取充值订单明细")
    public CreditOrders getCreditOrders(@PathVariable @ApiParam("用户ID") String userId) {
        log.info("[START] Get credit orders by user id: {}", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        CreditOrders orders = service.getCreditOrders(value);
        log.info("[END] Get credit orders with: {}", orders);
        return orders;
    }

    @GetMapping("/info/{userId}")
    @ApiOperation(value = "获取会员积分资料（后台）")
    public MemberPointsInfo getUserPointsInfo(@PathVariable @ApiParam("用户ID") String userId) {
        log.info("[START] Get user points info by user id: {}", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        MemberPointsInfo memberPointsInfo = service.getUserPointsInfo(value);
        log.info("[END] Get user points info with info: {}", memberPointsInfo);
        return memberPointsInfo;
    }
}
