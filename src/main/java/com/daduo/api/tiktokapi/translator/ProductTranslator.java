package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.entity.ProductOrder;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.repository.ProductOrderRepository;
import com.daduo.api.tiktokapi.repository.ProductRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ProductTranslator {
    private static final double TEN_THOUSAND = 10000.0;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Products translateToProducts(Page<Product> products) {
        Products result = new Products();
        List<ProductData> data = new ArrayList<>();
        for (Product product : products.getContent()) {
            ProductData productData = toProductData(product);
            data.add(productData);
        }
        result.setData(data);
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(products.getNumber());
        meta.setPageSize(products.getSize());
        meta.setTotalElements(products.getTotalElements());
        meta.setTotalPages(products.getTotalPages());
        result.setMeta(meta);
        return result;
    }

    public Product toProduct(ProductRequest request) {
        Product product = new Product();
        product.setId(getNewId());
        product.setLastModifiedTime(LocalDateTime.now());
        product.setCreatedTime(LocalDateTime.now());
        product.setImageUrl(request.getImageUrl());
        product.setPrice(request.getPrice());
        product.setStatus(request.getStatus());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCount(request.getCount());
        return product;
    }

    private int getNewId() {
        Product lastProduct = productRepository.findTop1ByOrderByLastModifiedTimeDesc();
        int lastId = 0;
        if (lastProduct != null) {
            lastId = lastProduct.getId();
        }
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return (year - 2000) * 10000 + lastId % 10000 + 1;
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
        productData.setId(Long.valueOf(product.getId()));
        productData.setImageUrl(product.getImageUrl());
        productData.setName(product.getName());
        productData.setStatus(product.getStatus());
        productData.setPrice(new BigDecimal(product.getPrice() / 10000.0));
        productData.setCount(product.getCount());
        productData.setCreatedTime(product.getCreatedTime().toDateTime());
        productData.setLastModifiedTime(product.getLastModifiedTime().toDateTime());
        return productData;
    }

    public ProductInfos toProductInfos(Page<Product> productPage) {
        ProductInfos result = new ProductInfos();
        List<ProductInfoData> data = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            ProductInfoData infoData = getProductInfoData(product);
            data.add(infoData);
        }
        result.setData(data);
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(productPage.getNumber());
        meta.setPageSize(productPage.getSize());
        meta.setTotalElements(productPage.getTotalElements());
        meta.setTotalPages(productPage.getTotalPages());
        result.setMeta(meta);
        return result;
    }

    public ProductInfoData getProductInfoData(Product product) {
        ProductInfoData infoData = new ProductInfoData();
        infoData.setDescription(product.getDescription());
        infoData.setId(Long.valueOf(product.getId()));
        infoData.setImageUrl(product.getImageUrl());
        infoData.setName(product.getName());
        infoData.setStatus(product.getStatus());
        infoData.setPrice(new BigDecimal(product.getPrice() / TEN_THOUSAND));
        infoData.setCount(product.getCount());
        infoData.setCreatedTime(product.getCreatedTime().toDateTime());
        infoData.setLastModifiedTime(product.getLastModifiedTime().toDateTime());
        List<ProductOrder> orders = productOrderRepository.findAllByProductId(product.getId());
        infoData.setTotalPrice(new BigDecimal((product.getPrice() * product.getCount()) / TEN_THOUSAND));
        infoData.setSaleCount(orders.size());
        infoData.setSaleAmount((orders.size() * product.getPrice()) / 10000);
        infoData.setUnSaleCount(product.getCount() - orders.size());
        return infoData;
    }
}
