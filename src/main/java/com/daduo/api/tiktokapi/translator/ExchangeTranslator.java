package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.ExchangeOrder;
import com.daduo.api.tiktokapi.entity.ProductOrder;
import com.daduo.api.tiktokapi.enums.OrderStatus;
import com.daduo.api.tiktokapi.model.ExchangeRequest;
import com.daduo.api.tiktokapi.model.ProductOrderResponse;
import org.springframework.stereotype.Component;

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
        return order;
    }
}
