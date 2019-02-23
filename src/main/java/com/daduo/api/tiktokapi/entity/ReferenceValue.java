package com.daduo.api.tiktokapi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "reference_value")
public class ReferenceValue {
    @Id
    @Column(name = "rmb", nullable = false)
    @ApiModelProperty(value = "1元等于多少积分")
    private int pointsOfPerRmb;

    @Column(name = "credit_of_per_rmb")
    @ApiModelProperty(value = "1元等于多少充值币")
    private int creditOfPerRmb;

    @Column(name = "points_of_per_tiktok_like")
    @ApiModelProperty(value = "抖音1赞等于多少积分")
    private int pointsOfPerTiktokLike;

    @Column(name = "points_of_per_tiktok_follow")
    @ApiModelProperty(value = "抖音1关注等于多少积分")
    private int pointsOfPerTiktokFollow;

    @Column(name = "points_of_per_tiktok_comment")
    @ApiModelProperty(value = "抖音1评论等于多少积分")
    private int pointsOfPerTiktokComment;

    @Column(name = "points_of_per_tiktok_play")
    @ApiModelProperty(value = "抖音1播放等于多少积分")
    private int pointsOfPerTiktokPlay;

    @Column(name = "points_of_per_tiktok_like_follow")
    @ApiModelProperty(value = "抖音1点赞加关注等于多少积分")
    private int pointsOfPerTiktokLikeAndFollow;

    @Column(name = "points_of_per_tiktok_like_follow_comment")
    @ApiModelProperty(value = "抖音1点赞加关注加评论等于多少积分")
    private int pointsOfPerTiktokLikeAndFollowAndComment;

    @Column(name = "points_of_per_kuaishou_like")
    @ApiModelProperty(value = "快手1赞等于多少积分")
    private int pointsOfPerKuaishouLike;

    @Column(name = "points_of_per_kuaishou_follow")
    @ApiModelProperty(value = "快手1关注等于多少积分")
    private int pointsOfPerKuaishouFollow;

    @Column(name = "points_of_per_kuaishou_comment")
    @ApiModelProperty(value = "快手1评论等于多少积分")
    private int pointsOfPerKuaishouComment;

    @Column(name = "points_of_per_kuaishou_play")
    @ApiModelProperty(value = "快手1播放等于多少积分")
    private int pointsOfPerKuaishouPlay;

    @Column(name = "points_of_per_kuaishou_like_follow")
    @ApiModelProperty(value = "快手1点赞加关注等于多少积分")
    private int pointsOfPerKuaishouLikeAndFollow;

    @Column(name = "points_of_per_kuaishou_like_follow_comment")
    @ApiModelProperty(value = "快手1点赞加关注加评论等于多少积分")
    private int pointsOfPerKuaishouLikeAndFollowAndComment;

    @Column(name = "commission_percent")
    @ApiModelProperty(value = "佣金百分比")
    private int commissionPercent;

    @Column(name = "presented_credit_for_10")
    @ApiModelProperty(value = "充10块送多少充值币")
    private int presentedCreditFor10;

    @Column(name = "presented_credit_for_30")
    @ApiModelProperty(value = "充30块送多少充值币")
    private int presentedCreditFor30;

    @Column(name = "presented_credit_for_50")
    @ApiModelProperty(value = "充50块送多少充值币")
    private int presentedCreditFor50;

    @Column(name = "presented_credit_for_100")
    @ApiModelProperty(value = "充100块送多少充值币")
    private int presentedCreditFor100;

    @Column(name = "presented_credit_for_200")
    @ApiModelProperty(value = "充200块送多少充值币")
    private int presentedCreditFor200;

    @Column(name = "presented_credit_for_500")
    @ApiModelProperty(value = "充500块送多少充值币")
    private int presentedCreditFor500;

    @Column(name = "presented_credit_for_1000")
    @ApiModelProperty(value = "充1000块送多少充值币")
    private int presentedCreditFor1000;

    @Column(name = "presented_credit_for_5000")
    @ApiModelProperty(value = "充5000块送多少充值币")
    private int presentedCreditFor5000;
}
