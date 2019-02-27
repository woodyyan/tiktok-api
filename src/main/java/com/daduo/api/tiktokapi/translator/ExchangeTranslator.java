package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ExchangeOrder;
import com.daduo.api.tiktokapi.enums.OrderStatus;
import com.daduo.api.tiktokapi.model.ExchangeOrderData;
import com.daduo.api.tiktokapi.model.ExchangeOrders;
import com.daduo.api.tiktokapi.model.ExchangeRequest;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeTranslator {
    public ExchangeOrder translateToExchangeOrder(ExchangeRequest exchangeRequest) {
        ExchangeOrder order = new ExchangeOrder();
        order.setMethod(exchangeRequest.getExchangeMethod());
        order.setMoney(exchangeRequest.getMoney());
        order.setImageUrl(exchangeRequest.getOtherImageUrl());
        order.setPayQrCodeImageUrl(exchangeRequest.getPayQrCodeImageUrl());
        order.setUserId(exchangeRequest.getUserId());
        order.setStatus(OrderStatus.IN_REVIEW);
        order.setCreatedTime(LocalDateTime.now());
        order.setLastModifiedTime(LocalDateTime.now());
        return order;
    }

    public ExchangeOrders toExchangeOrders(List<ExchangeOrder> orders) {
        ExchangeOrders exchangeOrders = new ExchangeOrders();
        List<ExchangeOrderData> data = new ArrayList<>();
        for (ExchangeOrder order : orders) {
            ExchangeOrderData exchangeOrderData = new ExchangeOrderData();
            exchangeOrderData.setMoney(order.getMoney());
            exchangeOrderData.setId(order.getId());
            exchangeOrderData.setImageUrl(order.getImageUrl());
            exchangeOrderData.setMethod(order.getMethod());
            exchangeOrderData.setPayQrCodeImageUrl(order.getPayQrCodeImageUrl());
            exchangeOrderData.setStatus(order.getStatus());
            exchangeOrderData.setUserId(order.getUserId());
            exchangeOrderData.setCreatedTime(order.getCreatedTime().toDateTime());
            exchangeOrderData.setLastModifiedTime(order.getLastModifiedTime().toDateTime());
            data.add(exchangeOrderData);
        }
        exchangeOrders.setData(data);
        return exchangeOrders;
    }
}
