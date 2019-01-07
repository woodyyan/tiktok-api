package com.daduo.api.tiktokapi.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "title", "details"})
@Data
public class Error {
    @ApiModelProperty(value = "HTTP status", example = "400", required = true)
    private String status;

    @ApiModelProperty(value = "Error title", example = "Bad request", required = true)
    private String title;

    @ApiModelProperty(value = "Error details", example = "A field is invalid")
    private String details;
}
