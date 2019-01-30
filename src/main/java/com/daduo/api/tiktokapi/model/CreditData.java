package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel(value = "充值币数据")
public class CreditData {
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "充值币")
    private Long credit;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "修改时间")
    private DateTime lastModifiedTime;
}
