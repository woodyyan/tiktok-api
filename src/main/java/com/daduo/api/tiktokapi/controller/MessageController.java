package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.MessageRequest;
import com.daduo.api.tiktokapi.model.MessageResponse;
import com.daduo.api.tiktokapi.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message")
@RestController
@Slf4j
@Api(tags = "消息接口", description = "消息管理")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping
    @ApiOperation("创建消息")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createMessage(@RequestBody @ApiParam("消息请求") MessageRequest request) {
        log.info("[START] Create message with request: {}", request);
        MessageResponse response = service.createMessage(request);
        log.info("[END] Create message with response: {}", response);
        return response;
    }
}
