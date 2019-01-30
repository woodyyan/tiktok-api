package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.model.Products;
import com.daduo.api.tiktokapi.repository.ProductRepository;
import com.daduo.api.tiktokapi.translator.ProductTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            products.add(product1);
            Product product2 = new Product();
            product2.setDescription("这是商品描述2");
            product2.setId(2L);
            product2.setName("雨伞");
            product2.setPrice(12D);
            product2.setImageUrl("");
            products.add(product2);
        }
        return translator.translateToProducts(products);
    }
}
