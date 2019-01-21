package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("登陆结果返回Json")
public class LoginResponse {
    @ApiModelProperty(value = "用户名", example = "张三")
    private String username;
    @ApiModelProperty(value = "用户ID", example = "1234567890")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "最新更新时间")
    private DateTime lastModifiedTime;
    @ApiModelProperty(value = "手机号码", example = "13888888888")
    private Long phoneNumber;
    @ApiModelProperty(value = "登陆凭证")
    private String sessionToken;
    @ApiModelProperty(value = "微信登陆信息")
    private AuthData authData;
}
