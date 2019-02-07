package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("消息返回")
public class MessageResponse {
    @ApiModelProperty(value = "消息数据")
    private MessageData data;
}
