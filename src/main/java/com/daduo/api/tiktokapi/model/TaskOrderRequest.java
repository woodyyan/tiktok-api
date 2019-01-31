package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("验证任务请求Json")
public class TaskOrderRequest {
    @ApiModelProperty(value = "任务ID", example = "1")
    private Long taskId;
    @ApiModelProperty(value = "任务截图OSS链接")
    private String imageUrl;
    @ApiModelProperty(value = "完成任务用户ID")
    private Long userId;
}
