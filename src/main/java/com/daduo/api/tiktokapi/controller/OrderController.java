package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.enums.OrderStatus;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.service.OrderService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @ApiOperation(value = "创建兑换现金订单", notes = "付款方式ExchangeMethod：WECHAT, ALIPAY")
    public ExchangeResponse createExchangeMoneyOrder(@RequestBody @ApiParam("兑换现金请求") ExchangeRequest exchangeRequest) {
        log.info("[START] Create exchange money order with request: {}", exchangeRequest);
        ExchangeResponse response = service.createExchangeMoneyOrder(exchangeRequest);
        log.info("[END] Create exchange money order with response: {}", response);
        return response;
    }

    @GetMapping("/exchange/{userId}")
    @ApiOperation(value = "获取兑换现金订单")
    public ExchangeOrders getExchangeMoneyOrders(@PathVariable @ApiParam("用户ID") Long userId) {
        log.info("[START] Get exchange money orders with user id: {}", userId);
        ExchangeOrders response = service.getExchangeMoneyOrders(userId);
        log.info("[END] Get exchange money orders with response: {}", response);
        return response;
    }

    @PutMapping("/exchange/{id}")
    @ApiOperation(value = "审核通过兑换现金订单")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateExchangeOrderStatus(@PathVariable Long id, @RequestParam @ApiParam("状态") OrderStatus status) {
        log.info("[START] Update exchange money order status with status: {}", status);
        service.updateExchangeOrderStatus(id, status);
        log.info("[END] Update exchange money order status with status: {}", status);
    }

    @PostMapping("/product")
    @ApiOperation(value = "创建兑换商品订单", notes = "已通过=ACCEPTED，待审核=IN_REVIEW, 已拒绝=REJECTED")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductOrderResponse createProductOrder(@RequestBody @ApiParam("订单请求") ProductOrderRequest productOrderRequest) {
        log.info("[START] Create order with request: {}", productOrderRequest);
        ProductOrderResponse response = service.createProductOrder(productOrderRequest);
        log.info("[END] Create order with response: {}", response);
        return response;
    }

    @PutMapping("/product/{productOrderId}")
    @ApiOperation(value = "更新兑换商品订单", notes = "已通过=ACCEPTED，待审核=IN_REVIEW, 已拒绝=REJECTED")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ProductOrderResponse updateProductOrder(@PathVariable @ApiParam("商品订单ID") Integer productOrderId, @RequestBody @ApiParam("订单请求") ProductOrderRequest productOrderRequest) {
        log.info("[START] Update product order with request: {}", productOrderRequest);
        ProductOrderResponse response = service.updateProductOrder(productOrderId, productOrderRequest);
        log.info("[END] Update product order with response: {}", response);
        return response;
    }

    @GetMapping("/product")
    @ApiOperation(value = "获取所有兑换商品订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public ProductOrders getAllProductOrders(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                             @ApiParam(value = "分页")
                                                     Pageable page) {
        log.info("[START] Get all product orders with paging: {}", page);
        ProductOrders response = service.getProductOrders(page);
        log.info("[END] Get all product orders with response: {}", response);
        return response;
    }
}
