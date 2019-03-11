package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "其他数据明细数据")
public class OtherDataDetailData {
    @ApiModelProperty(value = "会员ID")
    private Long accountId;
    @ApiModelProperty(value = "会员昵称")
    private String accountNickname;

    @ApiModelProperty(value = "充值币余额")
    private int creditBalance;
    @ApiModelProperty(value = "充值币总额")
    private int creditSum;

    @ApiModelProperty(value = "积分余额")
    private int pointsBalance;
    @ApiModelProperty(value = "积分总额")
    private int pointsSum;

    @ApiModelProperty(value = "刷粉额")
    private int taskCash;

    @ApiModelProperty(value = "刷粉积分")
    private int taskPoints;

    @ApiModelProperty(value = "刷单额")
    private int taskOrderCash;

    @ApiModelProperty(value = "刷单积分")
    private int taskOrderPoints;

    @ApiModelProperty(value = "兑换现金积分额")
    private int exchangeCashPoints;

    @ApiModelProperty(value = "兑换商品积分额")
    private int exchangeProductPoints;

    @ApiModelProperty(value = "佣金积分额")
    private int commissionPoints;

    @ApiModelProperty(value = "自动刷积分额")
    private int autoTaskPoints;

    @ApiModelProperty(value = "扣除会员积分额")
    private int costAccountPoints;

    @ApiModelProperty(value = "充值赠送充值币额")
    private int presentedCredit;
}
