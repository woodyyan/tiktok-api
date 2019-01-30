package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ExchangeOrder;
import com.daduo.api.tiktokapi.model.ExchangeRequest;
import com.daduo.api.tiktokapi.model.ExchangeResponse;
import com.daduo.api.tiktokapi.repository.ExchangeOrderRepository;
import com.daduo.api.tiktokapi.translator.ExchangeTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ExchangeTranslator translator;

    @Autowired
    private ExchangeOrderRepository repository;

    public ExchangeResponse createExchangeMoneyOrder(ExchangeRequest exchangeRequest) {
        ExchangeOrder order = translator.translateToExchangeOrder(exchangeRequest);
        repository.save(order);
        ExchangeResponse response = new ExchangeResponse();
        response.setMessage("提交成功，请等客服审核之后付款。");
        response.setStatus("SUCCESS");
        return response;
    }
}
