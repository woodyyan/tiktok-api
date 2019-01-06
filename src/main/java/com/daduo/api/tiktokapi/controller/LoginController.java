package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.WechatLoginRequest;
import com.daduo.api.tiktokapi.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    @ApiOperation(value = "Phone number login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("/wechat")
    @ApiOperation(value = "Wechat Login")
    public LoginResponse wechatLogin(@RequestBody WechatLoginRequest wechatLoginRequest) {
        return service.wechatLogin(wechatLoginRequest);

    }
}
