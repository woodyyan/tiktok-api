package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("任务返回Json")
public class TaskResponse {
    @ApiModelProperty(value = "任务数据")
    private TaskData data;
}
