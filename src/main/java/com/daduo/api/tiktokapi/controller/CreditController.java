package com.daduo.api.tiktokapi.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/credit")
@RestController
@Slf4j
@Api(tags = "充值币接口", description = "充值币管理")
public class CreditController {
}
