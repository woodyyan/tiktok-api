package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.PermissionType;
import com.daduo.api.tiktokapi.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("管理员请求")
public class AdminRequest {
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "手机号")
    private Long phoneNumber;
    @ApiModelProperty(value = "角色")
    private RoleType role;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "权限")
    private List<PermissionType> permissions;
}
