package com.daduo.api.tiktokapi.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("任务状态")
public enum TaskStatus {
    @ApiModelProperty(value = "进行中")
    IN_PROGRESS,
    @ApiModelProperty(value = "已完成")
    COMPLETED,
    @ApiModelProperty(value = "已终止")
    TERMINATED
}
