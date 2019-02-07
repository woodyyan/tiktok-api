package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "消息请求Json")
public class MessageRequest {
    @ApiModelProperty(value = "内容")
    private String content;
}
