package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.ProductOrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品订单请求")
public class ProductOrderRequest {
    @ApiModelProperty(value = "商品ID")
    private Integer productId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "数量")
    private Integer count;
    @ApiModelProperty(value = "总金额")
    private Integer totalPrice;
    @ApiModelProperty(value = "状态")
    private ProductOrderStatus status;
}
