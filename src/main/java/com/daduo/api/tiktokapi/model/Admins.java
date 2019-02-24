package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("管理员们")
public class Admins {
    @ApiModelProperty(value = "管理员数据")
    private List<AdminData> data = new ArrayList<>();
    @ApiModelProperty(value = "分页数据")
    private PagingMeta meta = new PagingMeta();
}
