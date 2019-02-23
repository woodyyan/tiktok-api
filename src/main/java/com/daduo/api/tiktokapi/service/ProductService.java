package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.model.ProductRequest;
import com.daduo.api.tiktokapi.model.ProductResponse;
import com.daduo.api.tiktokapi.model.Products;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.ProductRepository;
import com.daduo.api.tiktokapi.translator.ProductTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductTranslator translator;

    public Products getAllProducts() {
        List<Product> products = repository.findAll();
        //TODO
        if (products.size() == 0) {
            Product product1 = new Product();
            product1.setDescription("这是商品描述");
            product1.setId(1L);
            product1.setName("iPhone");
            product1.setPrice(10D);
            product1.setImageUrl("");
            product1.setCreatedTime(LocalDateTime.now());
            product1.setLastModifiedTime(LocalDateTime.now());
            products.add(product1);
            Product product2 = new Product();
            product2.setDescription("这是商品描述2");
            product2.setId(2L);
            product2.setName("雨伞");
            product2.setPrice(12D);
            product2.setImageUrl("");
            product2.setCreatedTime(LocalDateTime.now());
            product2.setLastModifiedTime(LocalDateTime.now());
            products.add(product2);

        }
        return translator.translateToProducts(products);
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
