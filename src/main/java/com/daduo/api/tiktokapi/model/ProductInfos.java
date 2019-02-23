package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("商品和统计数据JSON")
public class ProductInfos {
    @ApiModelProperty(value = "商品列表")
    private List<ProductInfoData> data = new ArrayList<>();
    @ApiModelProperty(value = "分页数据")
    private PagingMeta meta = new PagingMeta();
}
