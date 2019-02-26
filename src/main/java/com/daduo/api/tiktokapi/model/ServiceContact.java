package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("客服信息")
public class ServiceContact {
    @ApiModelProperty(value = "微信")
    private String wechat;
    @ApiModelProperty(value = "QQ")
    private String qq;
}
