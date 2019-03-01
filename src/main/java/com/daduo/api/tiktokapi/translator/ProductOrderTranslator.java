package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ProductOrder;
import com.daduo.api.tiktokapi.enums.ProductOrderStatus;
import com.daduo.api.tiktokapi.model.*;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductOrderTranslator {

    public ProductOrder translate(ProductOrderRequest productOrderRequest) {
        ProductOrder order = new ProductOrder();
        order.setProductId(productOrderRequest.getProductId());
        order.setUserId(productOrderRequest.getUserId());
        order.setPrice(productOrderRequest.getTotalPrice() != null ? productOrderRequest.getTotalPrice() : 0);
        order.setCount(productOrderRequest.getCount() != null ? productOrderRequest.getCount() : 0);
        order.setStatus(productOrderRequest.getStatus() != null ? productOrderRequest.getStatus() : ProductOrderStatus.IN_REVIEW);
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedTime(now);
        order.setLastModifiedTime(now);
        return order;
    }

    public ProductOrderResponse translateToResponse(ProductOrder productOrder) {
        ProductOrderResponse response = new ProductOrderResponse();
        ProductOrderData data = getProductOrderData(productOrder);
        response.setData(data);
        return response;
    }

    private ProductOrderData getProductOrderData(ProductOrder productOrder) {
        ProductOrderData data = new ProductOrderData();
        data.setId(productOrder.getId());
        data.setProductId(productOrder.getProductId());
        data.setUserId(productOrder.getUserId());
        data.setPrice(productOrder.getPrice());
        data.setCount(productOrder.getCount());
        data.setStatus(productOrder.getStatus());
        data.setCreatedTime(productOrder.getCreatedTime().toDateTime());
        data.setLastModifiedTime(productOrder.getLastModifiedTime().toDateTime());
        return data;
    }

    public ProductOrders toProductOrders(Page<ProductOrder> productOrderPage) {
        ProductOrders result = new ProductOrders();
        List<ProductOrderData> data = new ArrayList<>();
        for (ProductOrder productOrder : productOrderPage.getContent()) {
            ProductOrderData productInfoData = getProductOrderData(productOrder);
            data.add(productInfoData);
        }
        result.setData(data);
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(productOrderPage.getNumber());
        meta.setPageSize(productOrderPage.getSize());
        meta.setTotalElements(productOrderPage.getTotalElements());
        meta.setTotalPages(productOrderPage.getTotalPages());
        result.setMeta(meta);

        result.setTotalPoints(data.stream().mapToInt(ProductOrderData::getPrice).sum());
        return result;
    }
}
