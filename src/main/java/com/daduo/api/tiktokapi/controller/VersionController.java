package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.enums.OSPlatform;
import com.daduo.api.tiktokapi.model.VersionInfo;
import com.daduo.api.tiktokapi.service.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/version")
@RestController
@Slf4j
@Api(tags = "版本接口", description = "版本接口")
public class VersionController {

    @Autowired
    private VersionService service;

    @GetMapping
    @ApiOperation(value = "获取版本信息")
    public VersionInfo getNewVersion(@RequestParam(defaultValue = "iOS")
                                     @ApiParam(defaultValue = "iOS", required = true)
                                             OSPlatform platform,
                                     String currentVersion) {
        log.info("[START] Get new version with platform: {} and current version.", platform, currentVersion);
        VersionInfo info = service.getNewVersion(platform, currentVersion);
        log.info("[END] Get new version with info: {}.", info);
        return info;
    }
}
