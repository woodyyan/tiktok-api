package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品订单请求")
public class ProductOrderRequest {
    @ApiModelProperty(value = "商品ID")
    private Long productId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
