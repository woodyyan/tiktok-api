package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("注册结果返回Json")
public class SignUpResponse {
    @ApiModelProperty(value = "用户ID", example = "1234567890")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
}
