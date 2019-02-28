package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("商品订单列表")
public class ProductOrders {
    @ApiModelProperty(value = "商品订单数据")
    private List<ProductOrderData> data = new ArrayList<>();
    @ApiModelProperty(value = "分页")
    private PagingMeta meta;
    @ApiModelProperty(value = "总积分")
    private Integer totalPoints;
}
