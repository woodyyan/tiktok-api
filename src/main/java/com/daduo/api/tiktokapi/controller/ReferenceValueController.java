package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.ValueData;
import com.daduo.api.tiktokapi.model.ValueResponse;
import com.daduo.api.tiktokapi.service.ReferenceValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/reference/values")
@RestController
@Slf4j
@Api(tags = "基础数据接口", description = "基础数据管理")
public class ReferenceValueController {

    @Autowired
    private ReferenceValueService service;

    @GetMapping()
    @ApiOperation("获取基础数据设置")
    public ValueResponse getReferenceValues() {
        log.info("[START] Get reference values.");
        ValueData data = service.getReferenceValues();
        ValueResponse response = new ValueResponse();
        response.setData(data);
        log.info("[END] Get reference values with response: {}", response);
        return response;
    }

    //TODO 修改
}
