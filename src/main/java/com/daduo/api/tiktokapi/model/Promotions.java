package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("推广")
public class Promotions {
    @ApiModelProperty(value = "推广数据们")
    private List<PromotionData> data = new ArrayList<>();
}
