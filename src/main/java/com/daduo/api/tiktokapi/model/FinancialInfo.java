package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("财务信息")
public class FinancialInfo {
    @ApiModelProperty(value = "充值现金总额")
    private int topUpMoneyAmount;

    @ApiModelProperty(value = "佣金总额")
    private int commissionMoneyAmount;

    @ApiModelProperty(value = "扣除会员积分总额")
    private int moneyAmountWithoutPoints;

    @ApiModelProperty(value = "会员兑换现金总额")
    private int exchangeMoneyAmount;

    @ApiModelProperty(value = "会员兑换商品总额")
    private int exchangeProductAmount;

    @ApiModelProperty(value = "充值赠送积分总额")
    private int topUpPresentedCreditAmount;

    @ApiModelProperty(value = "毛利总额")
    private int grossProfitAmount;

    @ApiModelProperty(value = "充值币总额")
    private int creditAmount;

    @ApiModelProperty(value = "积分总额")
    private int pointsAmount;

    @ApiModelProperty(value = "扣除会员积分总额")
    private int pointsAmountWithoutPoints;

    @ApiModelProperty(value = "佣金积分总额")
    private int commissionPointsAmount;

    @ApiModelProperty(value = "充值币余额")
    private int creditBalance;

    @ApiModelProperty(value = "积分余额")
    private int pointsBalance;

    @ApiModelProperty(value = "会员兑换现金积分总额")
    private int exchangeMoneyPointsAmount;

    @ApiModelProperty(value = "会员兑换商品积分总额")
    private int exchangeProductPointsAmount;
}
