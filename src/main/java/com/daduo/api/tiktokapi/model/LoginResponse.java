package com.daduo.api.tiktokapi.model;

import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("登陆结果返回Json")
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends BaseModel {
    @ApiModelProperty(value = "昵称", example = "张三")
    private String nickname;
    @ApiModelProperty(value = "手机号码", example = "13888888888")
    private Long phoneNumber;
    @ApiModelProperty(value = "登陆凭证")
    private String sessionToken;
    @ApiModelProperty(value = "微信登陆信息")
    private AuthData authData;
    @ApiModelProperty(value = "激活状态")
    private AccountStatus status;
    @ApiModelProperty(value = "是否封号")
    private boolean isIsForbidden;
    @ApiModelProperty(value = "token")
    private String accessToken;
    @ApiModelProperty(value = "过期时间")
    private Long expiresIn;
    @ApiModelProperty(value = "openID")
    private String openId;
    @ApiModelProperty(value = "是否首次登陆")
    private boolean isFirst;
}
