package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理员登陆请求")
public class AdminLoginRequest {
    @ApiModelProperty(value = "手机号")
    private Long phoneNumber;
    @ApiModelProperty(value = "密码")
    private String password;
}
