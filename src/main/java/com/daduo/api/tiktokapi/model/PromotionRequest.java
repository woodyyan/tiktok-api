package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("推广请求")
public class PromotionRequest {
    @ApiModelProperty(value = "被推广人ID")
    private Long childUserId;
    @ApiModelProperty(value = "推广人ID")
    private Long promotionUserId;
}
