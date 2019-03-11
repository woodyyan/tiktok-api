package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class MainDataDetailData {
    private Long accountId;
    private String accountNickname;
    private int cash;
    private int commissionMoney;
    private int costPoints;
    private int autoTaskCash;
    private int exchangeCash;
    private int exchangeProductCash;
    private int presentedCreditCash;
}
