package com.daduo.api.tiktokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("价值Json")
public class ValueData {
    @ApiModelProperty(value = "1元等于多少积分")
    private double pointsOfPerRmb;
    @ApiModelProperty(value = "1元等于多少充值币")
    private double creditOfPerRmb;
    @ApiModelProperty(value = "抖音1赞等于多少积分")
    private double pointsOfPerTiktokLike;
    @ApiModelProperty(value = "抖音1关注等于多少积分")
    private double pointsOfPerTiktokFollow;
    @ApiModelProperty(value = "抖音1评论等于多少积分")
    private double pointsOfPerTiktokComment;
    @ApiModelProperty(value = "抖音1播放等于多少积分")
    private double pointsOfPerTiktokPlay;
    @ApiModelProperty(value = "快手1赞等于多少积分")
    private double pointsOfPerKuaishouLike;
    @ApiModelProperty(value = "快手1关注等于多少积分")
    private double pointsOfPerKuaishouFollow;
    @ApiModelProperty(value = "快手1评论等于多少积分")
    private double pointsOfPerKuaishouComment;
    @ApiModelProperty(value = "快手1播放等于多少积分")
    private double pointsOfPerKuaishouPlay;
    @ApiModelProperty(value = "佣金百分比")
    private int commissionPercent;
    @ApiModelProperty(value = "充10块送多少充值币")
    private double presentedCreditFor10;
    @ApiModelProperty(value = "充30块送多少充值币")
    private double presentedCreditFor30;
    @ApiModelProperty(value = "充50块送多少充值币")
    private double presentedCreditFor50;
    @ApiModelProperty(value = "充100块送多少充值币")
    private double presentedCreditFor100;
    @ApiModelProperty(value = "充200块送多少充值币")
    private double presentedCreditFor200;
    @ApiModelProperty(value = "充500块送多少充值币")
    private double presentedCreditFor500;
    @ApiModelProperty(value = "充1000块送多少充值币")
    private double presentedCreditFor1000;
    @ApiModelProperty(value = "充5000块送多少充值币")
    private double presentedCreditFor5000;
}
