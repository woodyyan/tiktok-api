package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.service.OperateLogService;
import com.daduo.api.tiktokapi.service.ProductService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/store/product")
@RestController
@Slf4j
@Api(tags = "商城接口", description = "商城管理")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/sale")
    @ApiOperation("获取所有在售商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public Products getAllSaleProducts(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                       @ApiParam(value = "分页")
                                               Pageable page) {
        log.info("[START] Get all sale products with page: {}", page);
        Products products = service.getAllSaleProducts(page);
        log.info("[END] Get all sale products with response: {}", products);
        return products;
    }

    @GetMapping
    @ApiOperation("获取所有商品和统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public ProductInfos getAllProducts(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                       @ApiParam(value = "分页")
                                               Pageable page) {
        log.info("[START] Get all products with page: {}", page);
        ProductInfos allProducts = service.getAllProducts(page);
        log.info("[END] Get all products with response: {}", allProducts);
        return allProducts;
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索商品", notes = "搜索商品")
    public ProductInfos searchProduct(@RequestParam @ApiParam("关键词") String  keyword) {
        log.info("[START] Search product with keyword: {}", keyword);
        ProductInfos response = service.searchProduct(keyword);
        log.info("[END] Search product with response: {}", response);
        return response;
    }

    @PostMapping
    @ApiOperation(value = "添加商品", notes = "商品状态：ON_SALE是在售, SOLD_OUT是售完, OFF_SALE是下架")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        log.info("[START] Add product with request: {}", request);
        operateLogService.addOperateLog("添加商品", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        ProductResponse response = service.addProduct(request);
        log.info("[END] Add product with response: {}", response);
        return response;
    }

    @PutMapping("/{productId}")
    @ApiOperation(value = "修改商品", notes = "商品状态：ON_SALE是在售, SOLD_OUT是售完, OFF_SALE是下架")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponse modifyProduct(@PathVariable Integer productId, @RequestBody ProductRequest request) {
        log.info("[START] Modify product with id: {}, request: {}", productId, request);
        operateLogService.addOperateLog("修改商品", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        ProductResponse response = service.modifyProduct(productId, request);
        log.info("[END] Modify product with response: {}", response);
        return response;
    }

    @GetMapping("/{productId}")
    @ApiOperation(value = "获取单个商品", notes = "商品状态：ON_SALE是在售, SOLD_OUT是售完, OFF_SALE是下架")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductInfoResponse getProduct(@PathVariable Integer productId) {
        log.info("[START] Modify product with request: {}", productId);
        ProductInfoResponse response = service.getProduct(productId);
        log.info("[END] Modify product with response: {}", response);
        return response;
    }
}
