package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "消息数据")
public class MessageData extends BaseModel {
    @ApiModelProperty(value = "内容")
    private String content;
}
