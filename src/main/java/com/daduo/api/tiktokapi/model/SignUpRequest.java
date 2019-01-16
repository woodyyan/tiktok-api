package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "注册请求Json", description = "注册请求Json")
public class SignUpRequest {
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    @ApiModelProperty(value = "手机号码", required = true)
    private Long number;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
