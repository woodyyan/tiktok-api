package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import com.daduo.api.tiktokapi.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping("/code/{number}")
    @ApiOperation(value = "Send Authentication Code", notes = "Send message authentication code")
    public AuthenticationCodeResponse sendMessageAuthenticationCode(@PathVariable Long number) {
        return service.sendMessageAuthenticationCode(number);
    }

    @PostMapping
    @ApiOperation(value = "Sign Up")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return service.signUp(signUpRequest);
    }
}
