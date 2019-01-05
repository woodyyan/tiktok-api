package com.daduo.api.tiktokapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping("/message")
    public String sendMessageAuthenticationCode() {
        return "abc";
    }
}
