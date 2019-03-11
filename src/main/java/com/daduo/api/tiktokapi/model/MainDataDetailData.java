package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "主要数据明细数据")
public class MainDataDetailData {
    @ApiModelProperty(value = "会员ID")
    private Long accountId;
    @ApiModelProperty(value = "会员昵称")
    private String accountNickname;
    @ApiModelProperty(value = "充值现金额")
    private int cash;
    @ApiModelProperty(value = "佣金额")
    private int commissionMoney;
    @ApiModelProperty(value = "扣除积分额")
    private int costPoints;
    @ApiModelProperty(value = "自动刷任务额")
    private int autoTaskCash;
    @ApiModelProperty(value = "兑换现金额")
    private int exchangeCash;
    @ApiModelProperty(value = "兑换商品额")
    private int exchangeProductCash;
    @ApiModelProperty(value = "充值赠送充值币额")
    private int presentedCreditCash;
}
