package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("商品数据")
@EqualsAndHashCode(callSuper = true)
public class ProductData extends BaseModel {
    @ApiModelProperty(value = "商品描述")
    private String description;
    @ApiModelProperty(value = "商品图片URL")
    private String imageUrl;
    @ApiModelProperty(value = "商品名字")
    private String name;
    @ApiModelProperty(value = "商品价格")
    private Double price;
    @ApiModelProperty(value = "总数")
    private int count;
}
