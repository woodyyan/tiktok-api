package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.RoleType;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理员登陆返回")
public class AdminResponse extends BaseModel {
    @ApiModelProperty(value = "手机号")
    private Long phoneNumber;
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "角色")
    private RoleType role;
    @ApiModelProperty(value = "用户名")
    private String username;
}
