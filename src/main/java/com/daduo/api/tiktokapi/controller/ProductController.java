package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.Products;
import com.daduo.api.tiktokapi.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
