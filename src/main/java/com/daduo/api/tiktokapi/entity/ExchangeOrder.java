package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.ExchangeMethod;
import com.daduo.api.tiktokapi.enums.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exchange_order")
public class ExchangeOrder {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method")
    private ExchangeMethod method;

    @Column(name = "money")
    private Integer money;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "pay_qr_code_image_url")
    private String payQrCodeImageUrl;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "points")
    private Integer points;

    @Column(name = "created_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "Asia/Shanghai")
    })
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "Asia/Shanghai")
    })
    private LocalDateTime lastModifiedTime;
}
