package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.LocalDateTime;

@Data
@ApiModel("商品数据")
public class ProductData {
    @ApiModelProperty(value = "商品描述")
    private String description;
    @ApiModelProperty(value = "商品ID")
    private Long id;
    @ApiModelProperty(value = "商品图片URL")
    private String imageUrl;
    @ApiModelProperty(value = "商品名字")
    private String name;
    @ApiModelProperty(value = "商品价格")
    private Double price;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime lastModifiedTime;
}
