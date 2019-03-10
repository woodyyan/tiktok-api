package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("任务订单")
@EqualsAndHashCode(callSuper = true)
public class TaskOrderData extends BaseModel {
    @ApiModelProperty(value = "任务")
    private TaskData task;
    @ApiModelProperty(value = "完成任务用户ID")
    private Long userId;
    @ApiModelProperty(value = "订单状态")
    private TaskOrderStatus status;

    @ApiModelProperty(value = "评论截图")
    private String commentImage;

    @ApiModelProperty(value = "点赞截图")
    private String likeImage;

    @ApiModelProperty(value = "关注截图")
    private String followImage;
}
