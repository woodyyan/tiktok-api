package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel("账号Json")
@EqualsAndHashCode(callSuper = true)
public class AccountData extends BaseModel {

    @ApiModelProperty(value = "昵称", example = "张三")
    private String nickname;

    @ApiModelProperty(value = "电话号码", example = "13888888888")
    private Long phoneNumber;

    @ApiModelProperty(value = "QQ号")
    private Long qq;

    @ApiModelProperty(value = "微信号")
    private String wechat;

    @ApiModelProperty(value = "头像URL")
    private String avatar;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "状态")
    private AccountStatus status;

    @ApiModelProperty(value = "激活刷单")
    private Boolean canTask;

    @ApiModelProperty(value = "充值币")
    private BigDecimal credit;

    @ApiModelProperty(value = "积分")
    private BigDecimal points;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "收货电话")
    private Long shippingPhone;

    @ApiModelProperty(value = "是否在线")
    private boolean isOnline;

    @ApiModelProperty(value = "扣除充值币/万个")
    private int costCredit;

    @ApiModelProperty(value = "扣除积分/万个")
    private int costPoints;
}
