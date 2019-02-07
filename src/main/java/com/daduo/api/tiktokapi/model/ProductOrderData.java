package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("商品订单数据JSON")
@EqualsAndHashCode(callSuper = true)
public class ProductOrderData extends BaseModel {
    @ApiModelProperty(value = "商品ID")
    private Long productId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
