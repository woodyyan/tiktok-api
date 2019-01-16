package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.PlatformLoginRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
@Slf4j
@Api(tags = "登陆接口", description = "手机和第三方登陆")
public class LoginController {

    private static final int PHONE_NUMBER_LENGTH = 11;

    @Autowired
    private LoginService service;

    @PostMapping
    @ApiOperation(value = "手机号码登陆")
    public LoginResponse login(@RequestBody @ApiParam(value = "登陆请求Json") LoginRequest loginRequest) {
        log.info("[START] Login with request: {}", loginRequest);
        validatePhoneNumber(loginRequest.getPhoneNumber());
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
        validatePhoneNumber(number);
        AuthenticationCodeResponse response = service.sendMessageAuthenticationCode(number);
        log.info("[END] Send message authentication code with response: {}", response);
        return response;
    }

    private void validatePhoneNumber(Long phoneNumber) {
        if (phoneNumber.toString().length() != PHONE_NUMBER_LENGTH) {
            Error error = new Error();
            error.setStatus("400");
            error.setTitle("手机号码格式不对");
            error.setDetails("手机号码格式不对, 请重试。");
            throw new ErrorException(HttpStatus.BAD_REQUEST, error);
        }
    }
}
