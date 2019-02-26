package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.ServiceContact;
import com.daduo.api.tiktokapi.service.SettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
@Slf4j
@Api(tags = "系统设置接口", description = "系统设置相关")
public class SettingsController {

    @Autowired
    private SettingsService service;

    @GetMapping("/contact")
    @ApiOperation("获取客服联系方式")
    public ServiceContact getServiceContact() {
        log.info("[START] Get service contact.");
        ServiceContact contact = service.getServiceContact();
        log.info("[END] Get service contact with {}.", contact);
        return contact;
    }

    @PutMapping("/contact")
    @ApiOperation("更新客服联系方式")
    public ServiceContact updateServiceContact(@RequestBody @ApiParam("客服信息") ServiceContact serviceContact) {
        log.info("[START] Update service contact with {}.", serviceContact);
        ServiceContact newContact = service.updateServiceContact(serviceContact);
        log.info("[END] Update service contact with {}.", newContact);
        return newContact;
    }
}
