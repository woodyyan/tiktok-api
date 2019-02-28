package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.PromotionRequest;
import com.daduo.api.tiktokapi.model.Promotions;
import com.daduo.api.tiktokapi.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Promotions getPromotions(@PathVariable @ApiParam("用户ID") Long userId) {
        log.info("[START] Get promotion details with user id: {}.", userId);
        Promotions promotions = service.getPromotions(userId);
        log.info("[END] Get promotion details with response: {}.", promotions);
        return promotions;
    }

    @PostMapping
    @ApiOperation("推广绑定")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPromotion(@RequestBody @ApiParam("推广请求") PromotionRequest promotionRequest) {
        service.createPromotion(promotionRequest);
    }
}
