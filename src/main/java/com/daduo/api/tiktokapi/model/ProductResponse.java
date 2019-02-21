package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品response")
public class ProductResponse {
    @ApiModelProperty(value = "商品数据")
    private ProductData data;
}
