package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.model.ProductData;
import com.daduo.api.tiktokapi.model.ProductRequest;
import com.daduo.api.tiktokapi.model.ProductResponse;
import com.daduo.api.tiktokapi.model.Products;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTranslator {
    public Products translateToProducts(List<Product> products) {
        Products result = new Products();
        List<ProductData> data = new ArrayList<>();
        for (Product product : products) {
            ProductData productData = toProductData(product);
            data.add(productData);
        }
        result.setData(data);
        return result;
    }

    public Product toProduct(ProductRequest request) {
        Product product = new Product();
        product.setLastModifiedTime(LocalDateTime.now());
        product.setCreatedTime(LocalDateTime.now());
        product.setImageUrl(request.getImageUrl());
        product.setPrice(request.getPrice());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCount(request.getCount());
        return product;
    }

    public ProductResponse toProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        ProductData data = toProductData(savedProduct);
        response.setData(data);
        return response;
    }

    private ProductData toProductData(Product product) {
        ProductData productData = new ProductData();
        productData.setDescription(product.getDescription());
        productData.setId(product.getId());
        productData.setImageUrl(product.getImageUrl());
        productData.setName(product.getName());
        productData.setPrice(product.getPrice());
        productData.setCount(product.getCount());
        productData.setCreatedTime(product.getCreatedTime().toDateTime());
        productData.setLastModifiedTime(product.getLastModifiedTime().toDateTime());
        return productData;
    }
}
