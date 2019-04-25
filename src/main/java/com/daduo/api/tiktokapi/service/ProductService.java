package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.enums.ProductStatus;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.ProductRepository;
import com.daduo.api.tiktokapi.translator.ProductTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductTranslator translator;

    public ProductInfos getAllProducts(Pageable page) {
        Page<Product> productPage;
        productPage = repository.findAll(page);
        return translator.toProductInfos(productPage);
    }

    public ProductInfos searchProduct(String keyword) {
        List<Product> products = repository.findByNameLike(keyword);
        return translator.toProductInfos(new PageImpl<>(products));
    }

    public ProductResponse addProduct(ProductRequest request) {
        Product product = translator.toProduct(request);
        Product savedProduct = repository.save(product);
        return translator.toProductResponse(savedProduct);
    }

    public ProductResponse modifyProduct(Integer productId, ProductRequest request) {
        Optional<Product> tempProduct = repository.findById(productId);
        if (tempProduct.isPresent()) {
            Product product = tempProduct.get();
            if (request.getCount() != null) {
                product.setCount(request.getCount());
            }
            if (request.getDescription() != null) {
                product.setDescription(request.getDescription());
            }
            if (request.getName() != null) {
                product.setName(request.getName());
            }
            if (request.getPrice() != null) {
                product.setPrice(request.getPrice());
            }
            if (request.getImageUrl() != null) {
                product.setImageUrl(request.getImageUrl());
            }
            product.setLastModifiedTime(LocalDateTime.now());
            if (request.getStatus() != null) {
                product.setStatus(request.getStatus());
            }
            Product savedProduct = repository.saveAndFlush(product);
            return translator.toProductResponse(savedProduct);
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("商品找不到。");
        }
    }

    public Products getAllSaleProducts(Pageable page) {
        Page<Product> productPage;
        productPage = repository.findAllByStatus(ProductStatus.ON_SALE, page);
        return translator.translateToProducts(productPage);
    }

    public ProductInfoResponse getProduct(Integer productId) {
        Optional<Product> product = repository.findById(productId);
        if (product.isPresent()) {
            ProductInfoData productInfoData = translator.getProductInfoData(product.get());
            ProductInfoResponse response = new ProductInfoResponse();
            response.setInfoData(productInfoData);
            return response;
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("商品找不到。");
        }
    }
}
