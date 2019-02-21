package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.ProductRequest;
import com.daduo.api.tiktokapi.model.ProductResponse;
import com.daduo.api.tiktokapi.model.Products;
import com.daduo.api.tiktokapi.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/store/product")
@RestController
@Slf4j
@Api(tags = "商城接口", description = "商城管理")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @ApiOperation("获取所有商品")
    public Products getAllProducts() {
        log.info("[START] Get all products");
        Products products = service.getAllProducts();
        log.info("[END] Get all products with response: {}", products);
        return products;
    }

    @PostMapping
    @ApiOperation(value = "添加商品", notes = "商品状态：SALE是在售, SOLD_OUT是售完, UNSHELVES是下架")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(ProductRequest request) {
        log.info("[START] Add product with request: {}", request);
        ProductResponse response = service.addProduct(request);
        log.info("[END] Add product with response: {}", response);
        return response;
    }
}
