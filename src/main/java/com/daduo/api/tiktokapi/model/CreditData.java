package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "充值币积分数据")
@EqualsAndHashCode(callSuper = true)
public class CreditData extends BaseModel {
    @ApiModelProperty(value = "充值币")
    private Integer credit;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "积分数")
    private Integer points;
    @ApiModelProperty(value = "可兑换金额")
    private int money;
}
