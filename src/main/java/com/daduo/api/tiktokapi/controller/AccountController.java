package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.service.AccountService;
import com.daduo.api.tiktokapi.service.OperateLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/account")
@RestController
@Slf4j
@Api(tags = "账号接口", description = "账号管理相关接口")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private OperateLogService operateLogService;

    @PutMapping("/{userId}")
    @ApiOperation(value = "更新账号信息")
    public AccountResponse updateAccount(@PathVariable Long userId, @RequestBody @ApiParam(value = "账号Json") AccountRequest accountRequest) {
        log.info("[START] Update account with user id: {}, and request: {}", userId, accountRequest);
        AccountData data = accountService.updateAccount(userId, accountRequest);
        log.info("[END] Update account with response: {}", data);
        AccountResponse response = new AccountResponse();
        response.setData(data);
        return response;
    }

    @PostMapping("/{userId}")
    @ApiOperation(value = "激活账号")
    public ActivationResult activateAccount(@PathVariable @ApiParam(value = "账号ID") Long userId) {
        log.info("[START] Activate account with userId: {}", userId);
        operateLogService.addOperateLog("激活账号", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        boolean isSuccess = accountService.activateAccount(userId);
        ActivationResult result = new ActivationResult();
        result.setSuccess(isSuccess);
        log.info("[END] Activated account with END: {}", result);
        return result;
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量激活账号（后台）")
    public ActivationResult batchActivateAccount(@RequestBody @ApiParam(value = "账号IDs") UserIds userIds) {
        log.info("[START] Activate account with userIds: {}", userIds);
        operateLogService.addOperateLog("批量激活账号", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        for (Long userId : userIds.getIds()) {
            accountService.activateAccount(userId);
        }
        ActivationResult result = new ActivationResult();
        result.setSuccess(true);
        log.info("[END] Activated account with result: {}", result);
        return result;
    }

    @PostMapping("/batch/task")
    @ApiOperation(value = "批量激活刷单（后台）")
    public ActivationResult batchActivateAccountTask(@RequestBody @ApiParam(value = "账号IDs") UserIds userIds) {
        log.info("[START] Activate account task with userIds: {}", userIds);
        operateLogService.addOperateLog("批量激活刷单", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        for (Long userId : userIds.getIds()) {
            accountService.activateAccountTask(userId);
        }
        ActivationResult result = new ActivationResult();
        result.setSuccess(true);
        log.info("[END] Activated account task with result: {}", result);
        return result;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "获取账号信息")
    public AccountResponse getAccount(@PathVariable Long userId) {
        log.info("[START] Update account with user id: {}.", userId);
        AccountData data = accountService.getAccount(userId);
        log.info("[END] Update account with response: {}", data);
        AccountResponse response = new AccountResponse();
        response.setData(data);
        return response;
    }

    @GetMapping
    @ApiOperation(value = "搜索账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public Accounts searchAccounts(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                   @ApiParam(value = "分页")
                                           Pageable page) {
        log.info("[START] Search account");
        Accounts accounts = accountService.searchAccount(page);
        log.info("[END] Search account with accounts: {}", accounts);
        return accounts;
    }

    @PostMapping("/online/{userId}")
    @ApiOperation(value = "保存在线心跳")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void storeOnlineStatus(@PathVariable @ApiParam("用户ID") Long userId) {
        log.info("[START] User id {} is online");
        accountService.storeOnlineStatus(userId);
        log.info("[END] User id {} is online");
    }

    @GetMapping("/online")
    @ApiOperation(value = "获取所有在线用户")
    public OnlineAccounts getAllOnlineAccounts() {
        log.info("[START] Get all online users");
        OnlineAccounts response = accountService.getAllOnlineAccounts();
        log.info("[END] Get all online users with response: {}", response);
        return response;
    }
}
