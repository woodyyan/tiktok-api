package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.ExchangeMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "兑换现金请求")
public class ExchangeRequest {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "兑换金额")
    private Integer money;
    @ApiModelProperty(value = "兑换方式")
    private ExchangeMethod exchangeMethod;
    @ApiModelProperty(value = "支付宝或者微信二维码地址")
    private String payQrCodeImageUrl;
    @ApiModelProperty(value = "另一张图片地址")
    private String otherImageUrl;
}
