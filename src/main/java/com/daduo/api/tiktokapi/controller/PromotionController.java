package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.Accounts;
import com.daduo.api.tiktokapi.model.AllPromotions;
import com.daduo.api.tiktokapi.model.PromotionRequest;
import com.daduo.api.tiktokapi.model.Promotions;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.service.PromotionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotion")
@Slf4j
@Api(tags = "推广接口", description = "推广相关")
public class PromotionController {

    @Autowired
    private PromotionService service;

    @GetMapping("/{userId}")
    @ApiOperation("获取推广明细")
    public Promotions getPromotions(@PathVariable @ApiParam("用户ID") String userId) {
        log.info("[START] Get promotion details with user id: {}.", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        Promotions promotions = service.getPromotions(value);
        log.info("[END] Get promotion details with response: {}.", promotions);
        return promotions;
    }

    @GetMapping
    @ApiOperation("获取全部推广明细（后台）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public AllPromotions getAllPromotions(@RequestParam(required = false) @ApiParam(value = "用户ID") String userId,
                                          @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                          @ApiParam(value = "分页")
                                                  Pageable page) {
        log.info("[START] Get all promotion details with page: {}.", page);
        Long value = null;
        if (!StringUtils.isEmpty(userId)) {
            try {
                value = Long.valueOf(userId);
            } catch (Exception ex) {
                Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
                throw new ErrorException(HttpStatus.OK, error);
            }
        }
        AllPromotions allPromotions = service.getAllPromotions(value, page);
        log.info("[END] Get all promotion details with response: {}.", allPromotions);
        return allPromotions;
    }

    @PostMapping
    @ApiOperation("推广绑定")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPromotion(@RequestBody @ApiParam("推广请求") PromotionRequest promotionRequest) {
        log.info("[START] Create promotion with request: {}.", promotionRequest);
        service.createPromotion(promotionRequest);
        log.info("[END] Create promotion with request: {}.", promotionRequest);
    }

    @GetMapping("/child/{userId}")
    @ApiOperation("获取所有下家（后台）")
    public Accounts getAllChildUsers(@PathVariable @ApiParam("用户ID") String userId) {
        log.info("[START] Get all child users with id: {}.", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        Accounts accounts = service.getAllChildUsers(value);
        log.info("[END] Get all child users with acounts: {}.", accounts);
        return accounts;
    }
}
