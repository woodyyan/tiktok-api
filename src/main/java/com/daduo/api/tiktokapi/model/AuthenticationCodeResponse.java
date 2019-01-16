package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("验证码发送结果Json")
public class AuthenticationCodeResponse {
    @ApiModelProperty(value = "是否成功", example = "True")
    private boolean isSuccess;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "消息")
    private String message;
}
