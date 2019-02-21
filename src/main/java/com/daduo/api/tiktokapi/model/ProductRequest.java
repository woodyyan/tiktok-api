package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.ProductStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品请求")
public class ProductRequest {
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;
    @ApiModelProperty(value = "价格")
    private Double price;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "总数")
    private int count;
    @ApiModelProperty(value = "状态")
    private ProductStatus status;
}
