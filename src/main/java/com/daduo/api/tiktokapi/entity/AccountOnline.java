package com.daduo.api.tiktokapi.entity;

import lombok.Data;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_online")
public class AccountOnline {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_time")
    private LocalDateTime createdTime;
}