package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("后台推广数据")
public class PromotionInfoData {
    @ApiModelProperty(value = "推广人ID")
    private Long userId;
    @ApiModelProperty(value = "推广人昵称")
    private String userNickname;
    @ApiModelProperty(value = "注册时间")
    private DateTime registerTime;
    @ApiModelProperty(value = "推广人数")
    private int promotionCount;
}
