package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import com.daduo.api.tiktokapi.service.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@Slf4j
@Api(tags = "注册接口", description = "手机号码注册")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping("/code/{number}")
    @ApiOperation(value = "发送手机验证码")
    public AuthenticationCodeResponse sendMessageAuthenticationCode(@PathVariable @ApiParam(value = "手机号码") Long number) {
        log.info("[START] Send message authentication code with number: {}", number);
        AuthenticationCodeResponse response = service.sendMessageAuthenticationCode(number);
        log.info("[END] Send message authentication code with response: {}", response);
        return response;
    }

    @PostMapping
    @ApiOperation(value = "注册")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(@RequestBody @ApiParam(value = "注册请求Json") SignUpRequest signUpRequest) {
        log.info("[START] Sign up with request: {}", signUpRequest);
        SignUpResponse response = service.signUp(signUpRequest);
        log.info("[END] Sign up with response: {}", response);
        return response;
    }
}
