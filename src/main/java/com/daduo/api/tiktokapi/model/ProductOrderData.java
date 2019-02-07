package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("商品订单数据JSON")
public class ProductOrderData {
    @ApiModelProperty(value = "商品订单ID")
    private Long id;
    @ApiModelProperty(value = "商品ID")
    private Long productId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "更新时间")
    private DateTime lastModifiedTime;
}
