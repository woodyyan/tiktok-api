package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.AccountStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("账号请求")
public class AccountRequest {
    @ApiModelProperty(value = "昵称", example = "张三")
    private String nickname;

    @ApiModelProperty(value = "QQ号")
    private Long qq;

    @ApiModelProperty(value = "收货手机号")
    private Long shippingPhone;

    @ApiModelProperty(value = "微信号")
    private String wechat;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "头像URL")
    private String avatar;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "状态")
    private AccountStatus status;

    @ApiModelProperty(value = "激活刷单")
    private Boolean canTask;
}
