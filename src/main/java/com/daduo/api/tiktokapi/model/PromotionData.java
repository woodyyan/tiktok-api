package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("推广数据")
public class PromotionData {
    @ApiModelProperty(value = "被推广人昵称")
    private String childNickname;
    @ApiModelProperty(value = "被推广人Id")
    private Long childUserId;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "推广人ID")
    private Long promotionUserId;
}
