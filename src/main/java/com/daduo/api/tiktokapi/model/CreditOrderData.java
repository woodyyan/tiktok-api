package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("充值订单数据Data")
public class CreditOrderData {
    @ApiModelProperty(value = "充值币")
    private int credit;

    @ApiModelProperty(value = "赠送的充值币")
    private int presentedCredit;

    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;

    @ApiModelProperty(value = "修改时间")
    private DateTime lastModifiedTime;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "积分")
    private int points;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "金额")
    private int money;
}
