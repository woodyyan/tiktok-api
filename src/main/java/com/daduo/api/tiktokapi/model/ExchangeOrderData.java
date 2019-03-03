package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.ExchangeMethod;
import com.daduo.api.tiktokapi.enums.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel(value = "兑换现金订单数据")
public class ExchangeOrderData {
    @ApiModelProperty(value = "金额")
    private Integer money;
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "第二张图片链接")
    private String imageUrl;
    @ApiModelProperty(value = "付款方式")
    private ExchangeMethod method;
    @ApiModelProperty(value = "付款二维码图片链接")
    private String payQrCodeImageUrl;
    @ApiModelProperty(value = "订单状态")
    private OrderStatus status;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "会员昵称")
    private String accountNickname;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "修改时间")
    private DateTime lastModifiedTime;

    @ApiModelProperty(value = "积分")
    private Integer points;
}
