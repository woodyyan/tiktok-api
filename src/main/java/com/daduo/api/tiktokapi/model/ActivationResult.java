package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "激活Json")
public class ActivationResult {
    @ApiModelProperty(value = "激活成功与否")
    private boolean success;
}
