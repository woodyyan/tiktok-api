package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "第三方登陆Json")
public class AuthData {
    @ApiModelProperty(value = "微信的OpenID")
    private String openId;
    @ApiModelProperty(value = "微信的access token")
    private String accessToken;
    @ApiModelProperty(value = "微信的token的过期时间")
    private Long expiresIn;
}
