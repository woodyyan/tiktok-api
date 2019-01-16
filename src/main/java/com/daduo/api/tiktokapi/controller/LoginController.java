package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.PlatformLoginRequest;
import com.daduo.api.tiktokapi.service.LoginService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
@Slf4j
@Api(tags = "登陆接口", description = "手机和第三方登陆")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    @ApiOperation(value = "手机号码登陆")
    public LoginResponse login(@RequestBody @ApiParam(value = "登陆请求Json") LoginRequest loginRequest) {
        log.info("[START] Login with request: {}", loginRequest);
        LoginResponse response = service.login(loginRequest);
        log.info("[END] Login with response: {}", response);
        return response;
    }

    @PostMapping("/platform")
    @ApiOperation(value = "第三方登陆")
    public LoginResponse platformLogin(@RequestBody @ApiParam(value = "第三方登陆Json") PlatformLoginRequest platformLoginRequest) {
        log.info("[START] Platform login with request: {}", platformLoginRequest);
        LoginResponse response = service.platformLogin(platformLoginRequest);
        log.info("[END] Platform login with response: {}", response);
        return response;
    }

    @PostMapping("/code/{number}")
    @ApiOperation(value = "发送手机验证码")
    public AuthenticationCodeResponse sendMessageAuthenticationCode(@PathVariable @ApiParam(value = "手机号码") Long number) {
        log.info("[START] Send message authentication code with number: {}", number);
        AuthenticationCodeResponse response = service.sendMessageAuthenticationCode(number);
        log.info("[END] Send message authentication code with response: {}", response);
        return response;
    }
}
