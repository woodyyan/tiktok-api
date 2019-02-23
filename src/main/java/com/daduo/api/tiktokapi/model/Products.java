package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("商品Json")
public class Products {
    @ApiModelProperty(value = "商品列表")
    private List<ProductData> data = new ArrayList<>();
    @ApiModelProperty(value = "分页数据")
    private PagingMeta meta = new PagingMeta();
}
