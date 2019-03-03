package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.AllPromotions;
import com.daduo.api.tiktokapi.model.PromotionRequest;
import com.daduo.api.tiktokapi.model.Promotions;
import com.daduo.api.tiktokapi.service.PromotionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    @ApiOperation("获取全部推广明细（后台）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public AllPromotions getAllPromotions(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                          @ApiParam(value = "分页")
                                                  Pageable page) {
        log.info("[START] Get all promotion details with page: {}.", page);
        AllPromotions allPromotions = service.getAllPromotions(page);
        log.info("[END] Get all promotion details with response: {}.", allPromotions);
        return allPromotions;
    }

    @PostMapping
    @ApiOperation("推广绑定")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPromotion(@RequestBody @ApiParam("推广请求") PromotionRequest promotionRequest) {
        service.createPromotion(promotionRequest);
    }
}
