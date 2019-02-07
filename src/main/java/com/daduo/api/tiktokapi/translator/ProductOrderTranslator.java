package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ProductOrder;
import com.daduo.api.tiktokapi.model.ProductOrderData;
import com.daduo.api.tiktokapi.model.ProductOrderRequest;
import com.daduo.api.tiktokapi.model.ProductOrderResponse;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderTranslator {
    public ProductOrder translate(ProductOrderRequest productOrderRequest) {
        ProductOrder order = new ProductOrder();
        order.setProductId(productOrderRequest.getProductId());
        order.setUserId(productOrderRequest.getUserId());
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
