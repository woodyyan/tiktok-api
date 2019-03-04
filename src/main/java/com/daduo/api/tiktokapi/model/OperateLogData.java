package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel("任务订单")
public class OperateLogData {
    @ApiModelProperty(value = "管理员名字")
    private String adminName;
    @ApiModelProperty(value = "访问IP")
    private String IP;
    @ApiModelProperty(value = "记录时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "操作")
    private String operation;
}
