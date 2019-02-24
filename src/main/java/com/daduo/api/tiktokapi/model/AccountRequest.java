package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.AccountStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountRequest {
    @ApiModelProperty(value = "昵称", example = "张三")
    private String nickname;

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
}
