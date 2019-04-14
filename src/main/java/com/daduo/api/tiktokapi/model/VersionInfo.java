package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("版本信息")
public class VersionInfo {
    @ApiModelProperty(value = "是否需要升级")
    private boolean needUpdate;

    @ApiModelProperty(value = "更新描述")
    private String description;

    @ApiModelProperty(value = "更新链接")
    private String updateUrl;
}
