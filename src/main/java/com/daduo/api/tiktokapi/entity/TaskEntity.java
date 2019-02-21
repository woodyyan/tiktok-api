package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.PlatformType;
import com.daduo.api.tiktokapi.enums.TaskItem;
import com.daduo.api.tiktokapi.enums.TaskStatus;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @ElementCollection
    @Column(name = "items")
    private List<TaskItem> items;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "active")
    private boolean isActive = true;

    @Column(name = "url")
    private String url;

    @Column(name = "created_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime lastModifiedTime;

    @Column(name = "platform")
    private PlatformType platform;

    @Column(name = "count")
    private int count;
}
