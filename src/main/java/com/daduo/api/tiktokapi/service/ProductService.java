package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.enums.ProductStatus;
import com.daduo.api.tiktokapi.model.ProductRequest;
import com.daduo.api.tiktokapi.model.ProductResponse;
import com.daduo.api.tiktokapi.model.Products;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.ProductRepository;
import com.daduo.api.tiktokapi.translator.ProductTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductTranslator translator;

    public Products getAllProducts(ProductStatus status, Pageable page) {
        Page<Product> productPage;
        if (status != null) {
            productPage = repository.findAllByStatus(status, page);
        } else {
            productPage = repository.findAll(page);
        }
        return translator.translateToProducts(productPage);
    }

    public ProductResponse addProduct(ProductRequest request) {
        Product product = translator.toProduct(request);
        Product savedProduct = repository.save(product);
        return translator.toProductResponse(savedProduct);
    }

    public ProductResponse modifyProduct(Long productId, ProductRequest request) {
        Optional<Product> tempProduct = repository.findById(productId);
        if (tempProduct.isPresent()) {
            Product product = tempProduct.get();
            product.setCount(request.getCount());
            product.setDescription(request.getDescription());
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setImageUrl(request.getImageUrl());
            product.setLastModifiedTime(LocalDateTime.now());
            product.setStatus(request.getStatus());
            Product savedProduct = repository.saveAndFlush(product);
            return translator.toProductResponse(savedProduct);
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("商品找不到。");
        }
    }
}
