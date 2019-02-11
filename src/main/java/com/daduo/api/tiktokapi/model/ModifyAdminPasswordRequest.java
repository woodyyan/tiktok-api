package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改管理员密码请求")
public class ModifyAdminPasswordRequest {
    @ApiModelProperty(value = "管理员ID")
    private Long adminId;
    @ApiModelProperty(value = "新密码")
    private String password;
}
