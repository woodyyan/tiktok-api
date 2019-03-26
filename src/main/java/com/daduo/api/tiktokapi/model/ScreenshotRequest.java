package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("截屏")
public class ScreenshotRequest {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "任务ID")
    private Long taskId;
    @ApiModelProperty(value = "截屏状态")
    private boolean status;
}
