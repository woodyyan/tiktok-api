package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("用户财务数据")
public class UserFinancialInfoResponse {

    @ApiModelProperty(value = "用户财务数据列表")
    private List<UserFinancialInfo> data = new ArrayList<>();
}
