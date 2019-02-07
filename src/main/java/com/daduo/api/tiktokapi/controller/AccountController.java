package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AccountData;
import com.daduo.api.tiktokapi.model.AccountRequest;
import com.daduo.api.tiktokapi.model.AccountResponse;
import com.daduo.api.tiktokapi.model.ActivationResult;
import com.daduo.api.tiktokapi.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account")
@RestController
@Slf4j
@Api(tags = "账号接口", description = "账号管理相关接口")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PatchMapping
    @ApiOperation(value = "更新账号信息")
    public AccountResponse updateAccount(@RequestBody @ApiParam(value = "账号Json") AccountRequest accountRequest) {
        log.info("[START] Update account with request: {}", accountRequest);
        AccountData data = accountService.updateAccount(accountRequest);
        log.info("[END] Update account with response: {}", data);
        AccountResponse response = new AccountResponse();
        response.setData(data);
        return response;
    }

    @PostMapping("/{userId}")
    @ApiOperation(value = "激活账号")
    public ActivationResult activateAccount(@RequestParam @ApiParam(value = "账号ID") Long userId) {
        log.info("[START] Activate account with userId: {}", userId);
        boolean isSuccess = accountService.activateAccount(userId);
        ActivationResult result = new ActivationResult();
        result.setSuccess(isSuccess);
        log.info("[END] Activated account with END: {}", result);
        return result;
    }

    //TODO 搜索用户（会员管理）
}
