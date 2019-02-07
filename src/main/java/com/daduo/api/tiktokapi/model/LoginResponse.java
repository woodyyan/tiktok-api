package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登陆结果返回Json")
public class LoginResponse extends BaseModel {
    @ApiModelProperty(value = "用户名", example = "张三")
    private String username;
    @ApiModelProperty(value = "手机号码", example = "13888888888")
    private Long phoneNumber;
    @ApiModelProperty(value = "登陆凭证")
    private String sessionToken;
    @ApiModelProperty(value = "微信登陆信息")
    private AuthData authData;
}
