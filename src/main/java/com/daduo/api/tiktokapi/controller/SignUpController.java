package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AuthenticationCodeResult;
import com.daduo.api.tiktokapi.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping("/code/{number}")
    @ApiOperation(value = "Send Authentication Code", notes = "Send message authentication code")
    public AuthenticationCodeResult sendMessageAuthenticationCode(@PathVariable Integer number) {
        return service.sendMessageAuthenticationCode(number);
    }
}
