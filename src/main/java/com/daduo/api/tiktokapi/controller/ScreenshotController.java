package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.ScreenshotRequest;
import com.daduo.api.tiktokapi.service.ScreenshotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/screenshot")
@RestController
@Slf4j
@Api(tags = "截屏接口", description = "记录用户是否截屏")
public class ScreenshotController {

    @Autowired
    private ScreenshotService service;

    @PostMapping
    @ApiOperation(value = "记录用户是否截屏")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveScreenshotStatus(@RequestBody @ApiParam("截屏请求") ScreenshotRequest request) {
        log.info("[START] Save screenshot status with request: {}", request);
        service.saveScreenshotStatus(request);
        log.info("[END] Save screenshot status");
    }
}
