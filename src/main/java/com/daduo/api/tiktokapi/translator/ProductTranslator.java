package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.model.ProductData;
import com.daduo.api.tiktokapi.model.Products;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTranslator {
    public Products translateToProducts(List<Product> products) {
        Products result = new Products();
        List<ProductData> data = new ArrayList<>();
        for (Product product : products) {
            ProductData productData = new ProductData();
            productData.setDescription(product.getDescription());
            productData.setId(product.getId());
            productData.setImageUrl(product.getImageUrl());
            productData.setName(product.getName());
            productData.setPrice(product.getPrice());
            productData.setCount(product.getCount());
            productData.setCreatedTime(product.getCreatedTime().toDateTime());
            productData.setLastModifiedTime(product.getLastModifiedTime().toDateTime());
            data.add(productData);
        }
        result.setData(data);
        return result;
    }
}
