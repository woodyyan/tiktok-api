package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("任务Json")
public class TaskRequest {
    @ApiModelProperty(value = "任务名", example = "给林志玲点赞")
    private String name;
    @ApiModelProperty(value = "发布者用户ID", example = "1234567890")
    private Long ownerId;
    @ApiModelProperty(value = "任务描述", example = "比如如何做哪些任务")
    private String description;
    @ApiModelProperty(value = "抖音链接")
    private String url;
    @ApiModelProperty(value = "任务项")
    private List<TaskItem> items;
    @ApiModelProperty(value = "价格")
    private Double price;
    @ApiModelProperty(value = "任务状态")
    private TaskStatus status;
}
