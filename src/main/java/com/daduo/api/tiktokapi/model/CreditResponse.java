package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "充值币Json")
public class CreditResponse {
    @ApiModelProperty(value = "充值币数据")
    private CreditData data;
}
