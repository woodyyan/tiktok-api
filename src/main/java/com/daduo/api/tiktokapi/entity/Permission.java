package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.RoleType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @Column(name = "role", nullable = false)
    private RoleType role;

    //会员管理
    @Column(name = "can_member_manage")
    private boolean canMemberManage;

    //刷单管理
    @Column(name = "can_task_manage")
    private boolean canTaskManage;

    //刷粉管理
    @Column(name = "can_task_order_manage")
    private boolean canTaskOrderManage;

    //推广管理
    @Column(name = "can_promotion_manage")
    private boolean canPromotionManage;

    //自动刷单管理
    @Column(name = "can_auto_task_manage")
    private boolean canAutoTaskManage;

    //积分商城管理
    @Column(name = "can_credit_store_manage")
    private boolean canCreditStoreManage;

    @Column(name = "created_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime lastModifiedTime;
}
