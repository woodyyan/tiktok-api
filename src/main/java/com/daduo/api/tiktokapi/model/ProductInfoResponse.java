package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品信息response")
public class ProductInfoResponse {
    @ApiModelProperty(value = "商品信息数据")
    private ProductInfoData infoData;
}
