package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "充值币积分请求Json")
public class CreditRequest {
    @ApiModelProperty(value = "充值币")
    private Integer credit;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "积分数")
    private Integer points;
}
