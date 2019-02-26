package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.AccountStatus;
import lombok.Data;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "expires_in")
    private Long expiresIn;

    @Column(name = "created_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime", parameters = {
            @Parameter(name = "databaseZone", value = "Asia/Shanghai")
    })
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime", parameters = {
            @Parameter(name = "databaseZone", value = "Asia/Shanghai")
    })
    private LocalDateTime lastModifiedTime;

    @Column(name = "qq")
    private Long qq;

    @Column(name = "wechat")
    private String wechat;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private AccountStatus status;

    @Column(name = "name")
    private String name;

    @Column(name = "shipping_phone")
    private Long shippingPhone;

    @Column(name = "can_task")
    private Boolean canTask;
}
