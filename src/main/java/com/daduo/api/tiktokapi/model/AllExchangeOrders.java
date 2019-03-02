package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.List;

@Data
public class AllExchangeOrders {
    private PagingMeta meta;
    private List<ExchangeOrderData> data;
}
