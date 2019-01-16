package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登陆请求Json", description = "登陆请求Json")
public class LoginRequest {
    @ApiModelProperty(value = "手机号码", required = true, example = "13888888888")
    private Long phoneNumber;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
