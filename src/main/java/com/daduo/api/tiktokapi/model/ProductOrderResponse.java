package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品订单")
public class ProductOrderResponse {
    @ApiModelProperty(value = "商品订单数据")
    private ProductOrderData data;
}
