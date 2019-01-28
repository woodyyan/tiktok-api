package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("价值Json")
public class ValueData {
    @ApiModelProperty(value = "人民币")
    private double rmb;
    @ApiModelProperty(value = "积分")
    private double points;
    @ApiModelProperty(value = "充值币")
    private double credit;
}
