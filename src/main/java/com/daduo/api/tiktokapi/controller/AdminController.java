package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AdminLoginRequest;
import com.daduo.api.tiktokapi.model.AdminRequest;
import com.daduo.api.tiktokapi.model.AdminResponse;
import com.daduo.api.tiktokapi.model.ResetAdminPasswordRequest;
import com.daduo.api.tiktokapi.service.AdminService;
import com.daduo.api.tiktokapi.validator.AdminValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@Slf4j
@Api(tags = "管理员接口", description = "管理员管理")
public class AdminController {
    @Autowired
    private AdminService service;

    @Autowired
    private AdminValidator validator;

    @PostMapping("/login")
    @ApiOperation(value = "登陆接口")
    public AdminResponse login(@RequestBody @ApiParam(value = "登陆请求Json") AdminLoginRequest loginRequest) {
        log.info("[START] Admin login with request: {}", loginRequest);
        AdminResponse response = service.login(loginRequest);
        log.info("[END] Admin login with response: {}", response);
        return response;
    }

    @PostMapping
    @ApiOperation(value = "添加管理员接口")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AdminResponse addAdminUser(@RequestBody @ApiParam("管理员请求") AdminRequest request) {
        log.info("[START] Add admin user with request: {}", request);
        validator.validateExists(request.getPhoneNumber());
        AdminResponse response = service.addAdminUser(request);
        log.info("[END] Add admin user with response: {}", response);
        return response;
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码接口")
    public void resetPassword(@RequestBody @ApiParam("重置密码请求") ResetAdminPasswordRequest request) {
        log.info("[START] Reset admin password with request: {}", request);
        service.resetPassword(request);
        log.info("[END] Reset admin password.");
    }
}
