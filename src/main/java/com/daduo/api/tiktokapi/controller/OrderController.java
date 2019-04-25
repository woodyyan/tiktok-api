package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.enums.ProductOrderStatus;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.service.OperateLogService;
import com.daduo.api.tiktokapi.service.OrderService;
import com.daduo.api.tiktokapi.validator.AccountValidator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/order")
@RestController
@Slf4j
@Api(tags = "订单接口", description = "订单管理相关接口")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private OperateLogService operateLogService;

    @Autowired
    private AccountValidator accountValidator;

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
    public ExchangeOrders getExchangeMoneyOrders(@PathVariable @ApiParam("用户ID") String userId) {
        log.info("[START] Get exchange money orders with user id: {}", userId);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        ExchangeOrders response = service.getExchangeMoneyOrders(value);
        log.info("[END] Get exchange money orders with response: {}", response);
        return response;
    }

    @GetMapping("/exchange")
    @ApiOperation(value = "获取所有兑换现金订单（后台）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public AllExchangeOrders getAllExchangeMoneyOrders(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                                       @ApiParam(value = "分页")
                                                               Pageable page) {
        log.info("[START] Get all exchange money orders with page: {}", page);
        AllExchangeOrders response = service.getAllExchangeMoneyOrders(page);
        log.info("[END] Get all exchange money orders with response: {}", response);
        return response;
    }

    @PutMapping("/exchange/{id}")
    @ApiOperation(value = "审核通过兑换现金订单", notes = "已付COMPLETED, 未付IN_REVIEW, 拒付REJECTED")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateExchangeOrderStatus(@PathVariable Long id, @RequestBody @ApiParam("修改现金订单请求") ExchangeOrderRequest request) {
        log.info("[START] Update exchange money order status with status: {}", request);
        operateLogService.addOperateLog("审核通过兑换现金订单", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        service.updateExchangeOrderStatus(id, request.getStatus());
        log.info("[END] Update exchange money order status with status: {}", request);
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
        if (productOrderRequest.getStatus() != null && productOrderRequest.getStatus().equals(ProductOrderStatus.ACCEPTED)) {
            operateLogService.addOperateLog("通过兑换商品", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        } else if (productOrderRequest.getStatus() != null && productOrderRequest.getStatus().equals(ProductOrderStatus.REJECTED)) {
            operateLogService.addOperateLog("不通过兑换商品", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        }
        operateLogService.addOperateLog("更新兑换商品订单", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        ProductOrderResponse response = service.updateProductOrder(productOrderId, productOrderRequest);
        log.info("[END] Update product order with response: {}", response);
        return response;
    }

    @GetMapping("/product")
    @ApiOperation(value = "获取所有兑换商品订单（后台）")
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

    @GetMapping("/product/{userId}")
    @ApiOperation(value = "获取用户兑换商品订单明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public ProductOrders getUserProductOrders(@PathVariable @ApiParam("用户ID") String userId, @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
    @ApiParam(value = "分页")
            Pageable page) {
        log.info("[START] Get user product orders with id: {}, page: {}", userId, page);
        Long value;
        try {
            value = Long.valueOf(userId);
        } catch (Exception ex) {
            Error error = ErrorBuilder.buildInvalidParameterError("会员ID必须是数字。");
            throw new ErrorException(HttpStatus.OK, error);
        }
        ProductOrders response = service.getUserProductOrders(value, page);
        log.info("[END] Get user product orders with response: {}", response);
        return response;
    }
}
