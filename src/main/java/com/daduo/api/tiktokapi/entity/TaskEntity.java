package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.PlatformType;
import com.daduo.api.tiktokapi.enums.TaskStatus;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "need_like")
    private boolean needLike = false;

    @Column(name = "need_comment")
    private boolean needComment = false;

    @Column(name = "need_follow")
    private boolean needFollow = false;

    @Column(name = "need_play")
    private boolean needPlay = false;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "is_sticky")
    private boolean isSticky;

    @Column(name = "active")
    private boolean isActive = true;

    @Column(name = "url")
    private String url;

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

    @Column(name = "platform")
    private PlatformType platform;

    @Column(name = "count")
    private int count;
}
