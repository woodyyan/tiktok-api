package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.CreditData;
import com.daduo.api.tiktokapi.model.CreditRequest;
import com.daduo.api.tiktokapi.model.CreditResponse;
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
@Api(tags = "充值币接口", description = "充值币管理")
public class CreditController {
    @Autowired
    private CreditService service;

    @Autowired
    private CreditValidator validator;

    @Autowired
    private AccountValidator accountValidate;

    @GetMapping("/{userId}")
    @ApiOperation("获取当前ID的充值币")
    public CreditResponse getCreditByUserId(@PathVariable @ApiParam("用户ID") Long userId) {
        log.info("[START] Get credit with userId: {}", userId);
        accountValidate.validateUserIdExists(userId);
        CreditData data = service.getCreditById(userId);
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
        accountValidate.validateUserIdExists(creditRequest.getUserId());
        validator.validate(creditRequest);
        CreditData creditData = service.modifyCredit(creditRequest);
        CreditResponse response = new CreditResponse();
        response.setData(creditData);
        log.info("[END] Add credit with response: {}", response);
        return response;
    }
}
