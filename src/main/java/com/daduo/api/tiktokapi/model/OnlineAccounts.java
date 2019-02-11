package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("在线会员")
public class OnlineAccounts {
    @ApiModelProperty(value = "在线会员ID")
    private List<Long> onlineMemberIds = new ArrayList<>();
}
