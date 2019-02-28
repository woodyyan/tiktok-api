package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("任务订单列表")
public class TaskOrders {
    @ApiModelProperty(value = "数据")
    private List<TaskOrderData> data = new ArrayList<>();

    @ApiModelProperty(value = "总积分")
    private Integer totalPoints;

    @ApiModelProperty(value = "分页数据")
    private PagingMeta meta = new PagingMeta();
}
