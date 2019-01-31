package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task_order")
public class TaskOrder {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskEntity task;

    @Column(name = "status")
    private TaskOrderStatus status;
}
