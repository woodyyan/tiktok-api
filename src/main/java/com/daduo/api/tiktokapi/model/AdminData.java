package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.PermissionType;
import com.daduo.api.tiktokapi.enums.RoleType;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("管理员数据")
@EqualsAndHashCode(callSuper = true)
public class AdminData extends BaseModel {
    @ApiModelProperty(value = "权限")
    public List<PermissionType> permissions = new ArrayList<>();
    @ApiModelProperty(value = "手机号")
    private Long phoneNumber;
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "角色")
    private RoleType role;
    @ApiModelProperty(value = "昵称")
    private String nickname;
}F
