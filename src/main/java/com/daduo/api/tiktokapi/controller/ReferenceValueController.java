package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.ValueData;
import com.daduo.api.tiktokapi.model.ValueResponse;
import com.daduo.api.tiktokapi.model.ValueResponseRequest;
import com.daduo.api.tiktokapi.service.ReferenceValueService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping
    @ApiOperation(value = "修改基础数据设置", notes = "需要改哪些值就只传哪些值")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ValueResponse modifyReferenceValues(@RequestBody @ApiParam("基础数据修改请求体") ValueResponseRequest request) {
        log.info("[START] Modify reference values with request: {}", request);
        ValueData data = service.modifyReferenceValues(request);
        ValueResponse response = new ValueResponse();
        response.setData(data);
        log.info("[END] Modify reference values with response: {}", response);
        return response;
    }

    @GetMapping("/{name}")
    @ApiOperation("查询基础数据设置")
    public String getReferenceValues(@PathVariable @ApiParam("参数名") String name) {
        log.info("[START] Search reference value with name: {}", name);
        JsonObject value = service.searchReferenceValue(name);
        log.info("[END] Search reference value with value: {}", value.toString());
        return value.toString();
    }
}
