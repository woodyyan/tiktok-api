package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("兑换现金修改请求")
public class ExchangeOrderRequest {
    @ApiModelProperty(value = "状态")
    private OrderStatus status;
}
