package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("商品和统计数据")
@EqualsAndHashCode(callSuper = true)
public class ProductInfoData extends ProductData {
    @ApiModelProperty(value = "总额")
    private int totalPrice;
    @ApiModelProperty(value = "已售数量")
    private int saleCount;
    @ApiModelProperty(value = "已售总额")
    private int saleAmount;
    @ApiModelProperty(value = "未售数量")
    private int unSaleCount;
}
