package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("任务订单")
public class TaskOrderData {
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "任务")
    private TaskData task;
    @ApiModelProperty(value = "任务截图OSS链接")
    private String imageUrl;
    @ApiModelProperty(value = "完成任务用户ID")
    private Long userId;
    @ApiModelProperty(value = "订单状态")
    private TaskOrderStatus status;
}
