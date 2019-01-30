package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.AccountStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.LocalDateTime;

@Data
@ApiModel("账号Json")
public class AccountData {
    @ApiModelProperty(value = "ID", example = "1234567890")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "张三")
    private String username;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "上次更新时间")
    private LocalDateTime lastModifiedTime;

    @ApiModelProperty(value = "状态")
    private AccountStatus status;
}
