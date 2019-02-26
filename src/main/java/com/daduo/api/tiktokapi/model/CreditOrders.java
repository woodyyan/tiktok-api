package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("充值订单")
public class CreditOrders {
    @ApiModelProperty(value = "充值订单数据")
    private List<CreditOrderData> data = new ArrayList<>();
}
