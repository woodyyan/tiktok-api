package com.daduo.api.tiktokapi.entity;

import lombok.Data;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promotion_user_id")
    private Long promotionUserId;

    @Column(name = "child_user_id")
    private Long childUserId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "child_nickname")
    private String childNickname;
}
