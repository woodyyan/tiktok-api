package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("价值Json")
public class ValueResponse {
    @ApiModelProperty(value = "价值数据")
    private ValueData data;
}
