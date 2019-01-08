package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import com.daduo.api.tiktokapi.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@Slf4j
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping("/code/{number}")
    @ApiOperation(value = "Send Authentication Code", notes = "Send message authentication code")
    public AuthenticationCodeResponse sendMessageAuthenticationCode(@PathVariable Long number) {
        log.info("[START] Send message authentication code with number: {}", number);
        AuthenticationCodeResponse response = service.sendMessageAuthenticationCode(number);
        log.info("[END] Send message authentication code with response: {}", response);
        return response;
    }

    @PostMapping
    @ApiOperation(value = "Sign Up")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("[START] Sign up with request: {}", signUpRequest);
        SignUpResponse response = service.signUp(signUpRequest);
        log.info("[END] Sign up with response: {}", response);
        return response;
    }
}
