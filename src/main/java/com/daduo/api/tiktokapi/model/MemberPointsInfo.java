package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "会员积分信息")
public class MemberPointsInfo {
    //刷粉积分总额，刷单积分总额，兑换商品积分总额，兑换现金积分总额
    @ApiModelProperty(value = "刷粉积分总额")
    private int totalTaskPoints;

    @ApiModelProperty(value = "刷单积分总额")
    private int totalTaskOrderPoints;

    @ApiModelProperty(value = "兑换商品积分总额")
    private int totalExchangeProductPoints;

    @ApiModelProperty(value = "兑换现金积分总额")
    private int totalExchangeMoneyPoints;
}
