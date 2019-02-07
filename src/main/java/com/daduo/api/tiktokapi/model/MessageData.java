package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "消息数据")
@EqualsAndHashCode(callSuper = true)
public class MessageData extends BaseModel {
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
