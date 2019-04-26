package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(value = "充值币积分数据")
@EqualsAndHashCode(callSuper = true)
public class CreditData extends BaseModel {
    @ApiModelProperty(value = "充值币")
    private BigDecimal credit;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "积分数")
    private BigDecimal points;
    @ApiModelProperty(value = "可兑换金额")
    private int money;
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
