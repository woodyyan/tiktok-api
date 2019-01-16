package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页")
public class PagingMeta {
    @ApiModelProperty(value = "总页数", example = "100")
    private long totalPages;
    @ApiModelProperty(value = "总数", example = "999")
    private long totalElements;
    @ApiModelProperty(value = "第几页", example = "0")
    private long pageNumber;
    @ApiModelProperty(value = "一页的数量", example = "20")
    private long pageSize;
}
