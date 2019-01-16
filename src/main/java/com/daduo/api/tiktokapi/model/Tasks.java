package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("任务集合")
public class Tasks {
    @ApiModelProperty(value = "任务集合")
    private List<TaskData> data = new ArrayList<>();
    @ApiModelProperty(value = "分页数据")
    private PagingMeta meta = new PagingMeta();
}
