package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.entity.ProductOrder;
import com.daduo.api.tiktokapi.model.ProductOrderData;
import com.daduo.api.tiktokapi.model.ProductOrderRequest;
import com.daduo.api.tiktokapi.model.ProductOrderResponse;
import com.daduo.api.tiktokapi.repository.ProductRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductOrderTranslator {
    @Autowired
    private ProductRepository productRepository;

    public ProductOrder translate(ProductOrderRequest productOrderRequest) {
        ProductOrder order = new ProductOrder();
        order.setProductId(productOrderRequest.getProductId());
        order.setUserId(productOrderRequest.getUserId());
        Optional<Product> product = productRepository.findById(productOrderRequest.getProductId());
        order.setPrice(product.get().getPrice());
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedTime(now);
        order.setLastModifiedTime(now);
        return order;
    }

    public ProductOrderResponse translateToResponse(ProductOrder productOrder) {
        ProductOrderResponse response = new ProductOrderResponse();
        ProductOrderData data = new ProductOrderData();
        data.setId(productOrder.getId());
        data.setProductId(productOrder.getProductId());
        data.setUserId(productOrder.getUserId());
        data.setCreatedTime(productOrder.getCreatedTime().toDateTime());
        data.setLastModifiedTime(productOrder.getLastModifiedTime().toDateTime());
        response.setData(data);
        return response;
    }
}
