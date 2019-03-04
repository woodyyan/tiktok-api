package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task_order")
public class TaskOrder {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskEntity task;

    @Column(name = "status")
    private TaskOrderStatus status;

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
