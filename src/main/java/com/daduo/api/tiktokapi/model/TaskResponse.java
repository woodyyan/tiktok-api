package com.daduo.api.tiktokapi.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private String url;
    private List<TaskItem> items;
    private Integer price;
    private TaskStatus status;
    private DateTime createdTime;
}