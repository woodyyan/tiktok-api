package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "抖音统计数据")
public class DouyinData {
    @ApiModelProperty(value = "点赞")
    private double likeCount;
    @ApiModelProperty(value = "关注")
    private double followCount;
    @ApiModelProperty(value = "评论")
    private double commentCount;
    @ApiModelProperty(value = "点赞+关注")
    private double likeFollowCount;
    @ApiModelProperty(value = "点赞+关注+评论")
    private double likeFollowCommentCount;
    @ApiModelProperty(value = "播放量")
    private double playCount;
}
