package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("任务集合JSON")
public class Messages {
    @ApiModelProperty(value = "消息集合")
    private List<MessageData> data = new ArrayList<>();

    @ApiModelProperty(value = "分页数据")
    private PagingMeta meta;
}
