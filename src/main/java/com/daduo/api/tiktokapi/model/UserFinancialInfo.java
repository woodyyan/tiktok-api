package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户详细财务数据")
public class UserFinancialInfo {
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "充值现金额")
    private Integer topUpMoney;

    @ApiModelProperty(value = "佣金额")
    private Integer commissionMoney;

    @ApiModelProperty(value = "扣除积分额")
    private Integer moneyWithoutPoints;

    @ApiModelProperty(value = "兑换现金额")
    private Integer exchangeMoney;

    @ApiModelProperty(value = "自动刷任务额")
    private Integer autoTaskMoney = 0;

    @ApiModelProperty(value = "兑换商品额")
    private Integer productMoney;

    @ApiModelProperty(value = "充值赠送充值币额")
    private Integer presentedCreditMoney;
}
