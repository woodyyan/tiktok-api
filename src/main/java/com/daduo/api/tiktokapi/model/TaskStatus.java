package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("任务项")
public enum TaskStatus {
    @ApiModelProperty(value = "有效")
    ACTIVE,
    @ApiModelProperty(value = "无效")
    INACTIVE
}
