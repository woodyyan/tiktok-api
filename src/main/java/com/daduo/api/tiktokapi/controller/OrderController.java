package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.enums.OrderStatus;
import com.daduo.api.tiktokapi.model.ExchangeRequest;
import com.daduo.api.tiktokapi.model.ExchangeResponse;
import com.daduo.api.tiktokapi.model.ProductOrderRequest;
import com.daduo.api.tiktokapi.model.ProductOrderResponse;
import com.daduo.api.tiktokapi.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/exchange/{id}")
    @ApiOperation(value = "审核通过兑换现金订单")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateExchangeOrderStatus(@PathVariable Long id, @RequestParam @ApiParam("状态") OrderStatus status) {
        log.info("[START] Update exchange money order status with status: {}", status);
        service.updateExchangeOrderStatus(id, status);
        log.info("[END] Update exchange money order status with status: {}", status);
    }

    @PostMapping("/product")
    public ProductOrderResponse createProductOrder(@RequestBody @ApiParam("订单请求") ProductOrderRequest productOrderRequest) {
        log.info("[START] Create order with request: {}", productOrderRequest);
        ProductOrderResponse response = service.createProductOrder(productOrderRequest);
        log.info("[END] Create order with response: {}", response);
        return response;
    }
}
