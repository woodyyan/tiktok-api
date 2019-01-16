package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("任务项")
public enum TaskItem {
    @ApiModelProperty(value = "点赞")
    LIKE,
    @ApiModelProperty(value = "点击率")
    CLICK_RATE,
    @ApiModelProperty(value = "关注")
    FOLLOW,
    @ApiModelProperty(value = "评论")
    COMMENT
}
