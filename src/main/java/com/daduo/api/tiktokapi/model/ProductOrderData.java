package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.ProductOrderStatus;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

@Data
@ApiModel("商品订单数据JSON")
@EqualsAndHashCode(callSuper = true)
public class ProductOrderData extends BaseModel {
    @ApiModelProperty(value = "商品ID")
    private Integer productId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "单价（积分）")
    private Integer price;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "状态")
    private ProductOrderStatus status;

    @ApiModelProperty(value = "会员昵称")
    private String accountNickname;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品图")
    private String productImage;

    @ApiModelProperty(value = "支付时间")
    private DateTime payTime;

    @ApiModelProperty(value = "总积分")
    private Integer totalPoints;
}
