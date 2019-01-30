package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.ExchangeRequest;
import com.daduo.api.tiktokapi.model.ExchangeResponse;
import com.daduo.api.tiktokapi.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
@Slf4j
@Api(tags = "订单接口", description = "订单管理相关接口")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/exchange")
    @ApiOperation(value = "创建兑换现金订单")
    public ExchangeResponse createExchangeMoneyOrder(@RequestBody @ApiParam("兑换现金请求") ExchangeRequest exchangeRequest) {
        log.info("[START] Create exchange money order with request: {}", exchangeRequest);
        ExchangeResponse response = service.createExchangeMoneyOrder(exchangeRequest);
        log.info("[END] Create exchange money order with response: {}", response);
        return response;
    }

    public void updateExchangeOrder() {

    }
}
