package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("验证任务请求Json")
public class TaskOrderRequest {
    @ApiModelProperty(value = "任务ID", example = "1")
    private Long taskId;
    @ApiModelProperty(value = "完成任务用户ID")
    private Long userId;
    @ApiModelProperty(value = "任务状态")
    private TaskOrderStatus status;
}
