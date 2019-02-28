package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "兑换现金订单们")
public class ExchangeOrders {
    @ApiModelProperty(value = "兑换订单数据们")
    private List<ExchangeOrderData> data = new ArrayList<>();

    @ApiModelProperty(value = "总积分")
    private Integer totalPoints;
}
