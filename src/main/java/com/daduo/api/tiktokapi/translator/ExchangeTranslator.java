package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ExchangeOrder;
import com.daduo.api.tiktokapi.enums.OrderStatus;
import com.daduo.api.tiktokapi.model.*;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeTranslator {
    public ExchangeOrder translateToExchangeOrder(ExchangeRequest exchangeRequest, Integer points) {
        ExchangeOrder order = new ExchangeOrder();
        order.setMethod(exchangeRequest.getExchangeMethod());
        order.setMoney(exchangeRequest.getMoney());
        order.setPoints(points);
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
            ExchangeOrderData exchangeOrderData = toExchangeOrderData(order);
            data.add(exchangeOrderData);
        }
        exchangeOrders.setData(data);
        exchangeOrders.setTotalPoints(data.stream().mapToInt(ExchangeOrderData::getPoints).sum());
        return exchangeOrders;
    }

    public AllExchangeOrders toAllExchangeOrders(Page<ExchangeOrder> orders) {
        AllExchangeOrders result = new AllExchangeOrders();
        List<ExchangeOrderData> data = new ArrayList<>();
        for (ExchangeOrder order : orders) {
            ExchangeOrderData exchangeOrderData = toExchangeOrderData(order);
            data.add(exchangeOrderData);
        }
        result.setData(data);

        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(orders.getNumber());
        meta.setPageSize(orders.getSize());
        meta.setTotalElements(orders.getTotalElements());
        meta.setTotalPages(orders.getTotalPages());
        result.setMeta(meta);
        return result;
    }

    private ExchangeOrderData toExchangeOrderData(ExchangeOrder order) {
        ExchangeOrderData exchangeOrderData = new ExchangeOrderData();
        exchangeOrderData.setMoney(order.getMoney());
        exchangeOrderData.setId(order.getId());
        exchangeOrderData.setImageUrl(order.getImageUrl());
        exchangeOrderData.setMethod(order.getMethod());
        exchangeOrderData.setPayQrCodeImageUrl(order.getPayQrCodeImageUrl());
        exchangeOrderData.setStatus(order.getStatus());
        exchangeOrderData.setPoints(order.getPoints());
        exchangeOrderData.setUserId(order.getUserId());
        exchangeOrderData.setCreatedTime(order.getCreatedTime().toDateTime());
        exchangeOrderData.setLastModifiedTime(order.getLastModifiedTime().toDateTime());
        return exchangeOrderData;
    }
}
