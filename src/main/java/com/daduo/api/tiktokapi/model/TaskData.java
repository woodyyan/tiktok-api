package com.daduo.api.tiktokapi.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class TaskData {
    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    private String url;
    private List<TaskItem> items;
    private Double price;
    private TaskStatus status;
    private DateTime createdTime;
    private DateTime lastModifiedTime;
}
