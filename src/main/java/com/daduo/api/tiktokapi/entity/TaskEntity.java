package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.model.TaskItem;
import com.daduo.api.tiktokapi.model.TaskStatus;
import lombok.Data;
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

    @Column(name = "url")
    private String url;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}