package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.PlatformType;
import com.daduo.api.tiktokapi.enums.TaskItem;
import com.daduo.api.tiktokapi.enums.TaskStatus;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel("任务Json")
@EqualsAndHashCode(callSuper = true)
public class TaskData extends BaseModel {
    @ApiModelProperty(value = "创建者用户ID", example = "1234567890")
    private Long ownerId;
    @ApiModelProperty(value = "任务名字")
    private String name;
    @ApiModelProperty(value = "任务描述")
    private String description;
    @ApiModelProperty(value = "抖音URL")
    private String url;
    @ApiModelProperty(value = "任务项")
    private List<TaskItem> items;
    @ApiModelProperty(value = "任务价格")
    private Double price;
    @ApiModelProperty(value = "任务状态")
    private TaskStatus status;
    @ApiModelProperty(value = "平台")
    private PlatformType platform;
    @ApiModelProperty(value = "是否激活")
    private boolean isActive;
    @ApiModelProperty(value = "次数")
    private int count;
}
