package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "其他数据明细")
public class OtherDataDetail {
    @ApiModelProperty(value = "数据")
    private List<OtherDataDetailData> data = new ArrayList<>();

    @ApiModelProperty(value = "分页")
    private PagingMeta meta = new PagingMeta();
}
