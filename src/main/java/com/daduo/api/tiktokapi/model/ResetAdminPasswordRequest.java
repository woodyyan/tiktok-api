package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("重置管理员密码请求")
public class ResetAdminPasswordRequest {
    @ApiModelProperty(value = "电话号码")
    private Long phoneNumber;
    @ApiModelProperty(value = "验证码")
    private Integer code;
    @ApiModelProperty(value = "密码")
    private String password;
}
