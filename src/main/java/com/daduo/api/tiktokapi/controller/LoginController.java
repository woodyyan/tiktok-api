package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.PlatformLoginRequest;
import com.daduo.api.tiktokapi.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    @ApiOperation(value = "Phone number login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        log.info("[START] Login with request: {}", loginRequest);
        LoginResponse response = service.login(loginRequest);
        log.info("[END] Login with response: {}", response);
        return response;
    }

    @PostMapping("/platform")
    @ApiOperation(value = "Third-party Login")
    public LoginResponse platformLogin(@RequestBody PlatformLoginRequest platformLoginRequest) {
        log.info("[START] Platform login with request: {}", platformLoginRequest);
        LoginResponse response = service.platformLogin(platformLoginRequest);
        log.info("[END] Platform login with response: {}", response);
        return response;
    }
}
