package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "充值币积分返回Json")
public class CreditResponse {
    @ApiModelProperty(value = "充值币积分数据")
    private CreditData data;
}
