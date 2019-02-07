package com.daduo.api.tiktokapi.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class BaseModel {
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private DateTime createdTime;
    @ApiModelProperty(value = "更新时间")
    private DateTime lastModifiedTime;
}
