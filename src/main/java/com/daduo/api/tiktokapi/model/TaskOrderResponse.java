package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("任务订单返回")
public class TaskOrderResponse {
    @ApiModelProperty(value = "数据")
    private TaskOrderData data;

    @ApiModelProperty(value = "消息")
    private String message;
}
