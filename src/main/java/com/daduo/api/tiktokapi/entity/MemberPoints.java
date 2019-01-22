package com.daduo.api.tiktokapi.entity;

import lombok.Data;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "member_points")
public class MemberPoints {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "points", nullable = false)
    private Long points;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}
