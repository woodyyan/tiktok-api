package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.MessageData;
import com.daduo.api.tiktokapi.model.MessageRequest;
import com.daduo.api.tiktokapi.model.MessageResponse;
import com.daduo.api.tiktokapi.model.Messages;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.service.MessageService;
import com.daduo.api.tiktokapi.validator.AccountValidator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message")
@RestController
@Slf4j
@Api(tags = "消息接口", description = "消息管理")
public class MessageController {

    @Autowired
    private MessageService service;

    @Autowired
    private AccountValidator accountValidator;

    @PostMapping
    @ApiOperation("创建消息")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createMessage(@RequestBody @ApiParam("消息请求") MessageRequest request) {
        log.info("[START] Create message with request: {}", request);
        accountValidator.validateUserIdExists(request.getUserId());
        MessageData data = service.createMessage(request);
        MessageResponse response = new MessageResponse();
        response.setData(data);
        log.info("[END] Create message with response: {}", response);
        return response;
    }

    @GetMapping
    @ApiOperation("搜索消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public Messages searchMessage(@RequestParam @ApiParam("用户ID") String userId, @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
    @ApiParam(value = "分页")
            Pageable page) {
        log.info("[START] search message with user id: {}", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        Messages messages = service.searchMessage(value, page);
        log.info("[END] search message with messages: {}", messages);
        return messages;
    }
}
