package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AdminLoginRequest;
import com.daduo.api.tiktokapi.model.AdminLoginResponse;
import com.daduo.api.tiktokapi.service.AdminLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/login")
@RestController
@Slf4j
@Api(tags = "管理员登陆接口", description = "管理员登陆")
public class AdminLoginController {
    @Autowired
    private AdminLoginService service;

    @PostMapping
    @ApiOperation(value = "登陆接口")
    public AdminLoginResponse login(@RequestBody @ApiParam(value = "登陆请求Json") AdminLoginRequest loginRequest) {
        log.info("[START] Admin login with request: {}", loginRequest);
        AdminLoginResponse response = service.login(loginRequest);
        log.info("[END] Admin login with response: {}", response);
        return response;
    }
}
