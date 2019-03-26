package com.daduo.api.tiktokapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "screenshot_status")
public class ScreenshotStatus {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "status")
    private boolean status;
}
