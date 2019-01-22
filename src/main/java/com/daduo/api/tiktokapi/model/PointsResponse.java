package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("积分Json")
public class PointsResponse {
    @ApiModelProperty(value = "积分数据")
    private MemberPointsData data;
}
