package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("积分Json")
public class MemberPointsData {
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "修改时间")
    private DateTime lastModifiedTime;
    @ApiModelProperty(value = "Id")
    private Long id;
    @ApiModelProperty(value = "用户Id")
    private Long userId;
    @ApiModelProperty(value = "积分数")
    private Long points;
}
