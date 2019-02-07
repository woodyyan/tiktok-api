package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理员登陆返回")
public class AdminResponse {
    @ApiModelProperty(value = "管理员数据")
    private AdminData data;
}
