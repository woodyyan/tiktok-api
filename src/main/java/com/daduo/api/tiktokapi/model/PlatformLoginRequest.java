package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "第三方登陆请求Json")
public class PlatformLoginRequest {
    @ApiModelProperty(value = "第三方登陆信息")
    private AuthData authData;
    @ApiModelProperty(value = "平台名字", example = "wechat")
    private String platform;
}
