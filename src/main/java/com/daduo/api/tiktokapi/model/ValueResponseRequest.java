package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("基础价值请求")
public class ValueResponseRequest {
    @ApiModelProperty(value = "1元等于多少积分")
    private Integer pointsOfPerRmb;
    @ApiModelProperty(value = "1元等于多少充值币")
    private Integer creditOfPerRmb;
    @ApiModelProperty(value = "抖音1赞等于多少积分")
    private Integer pointsOfPerTiktokLike;
    @ApiModelProperty(value = "抖音1关注等于多少积分")
    private Integer pointsOfPerTiktokFollow;
    @ApiModelProperty(value = "抖音1评论等于多少积分")
    private Integer pointsOfPerTiktokComment;
    @ApiModelProperty(value = "抖音1播放等于多少积分")
    private Integer pointsOfPerTiktokPlay;

    @ApiModelProperty(value = "抖音1点赞加关注等于多少积分")
    private Integer pointsOfPerTiktokLikeAndFollow;
    @ApiModelProperty(value = "抖音1点赞加关注加评论等于多少积分")
    private Integer pointsOfPerTiktokLikeAndFollowAndComment;

    @ApiModelProperty(value = "快手1赞等于多少积分")
    private Integer pointsOfPerKuaishouLike;
    @ApiModelProperty(value = "快手1关注等于多少积分")
    private Integer pointsOfPerKuaishouFollow;
    @ApiModelProperty(value = "快手1评论等于多少积分")
    private Integer pointsOfPerKuaishouComment;
    @ApiModelProperty(value = "快手1播放等于多少积分")
    private Integer pointsOfPerKuaishouPlay;

    @ApiModelProperty(value = "快手1点赞加关注等于多少积分")
    private Integer pointsOfPerKuaishouLikeAndFollow;
    @ApiModelProperty(value = "快手1点赞加关注加评论等于多少积分")
    private Integer pointsOfPerKuaishouLikeAndFollowAndComment;

    @ApiModelProperty(value = "佣金百分比")
    private Integer commissionPercent;
    @ApiModelProperty(value = "充10块送多少充值币")
    private Integer presentedCreditFor10;
    @ApiModelProperty(value = "充30块送多少充值币")
    private Integer presentedCreditFor30;
    @ApiModelProperty(value = "充50块送多少充值币")
    private Integer presentedCreditFor50;
    @ApiModelProperty(value = "充100块送多少充值币")
    private Integer presentedCreditFor100;
    @ApiModelProperty(value = "充200块送多少充值币")
    private Integer presentedCreditFor200;
    @ApiModelProperty(value = "充500块送多少充值币")
    private Integer presentedCreditFor500;
    @ApiModelProperty(value = "充1000块送多少充值币")
    private Integer presentedCreditFor1000;
    @ApiModelProperty(value = "充5000块送多少充值币")
    private Integer presentedCreditFor5000;
}
