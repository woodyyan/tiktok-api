package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskRequest {
    private Long id;
    private String name;
    private String description;
    private String url;
    private List<TaskItem> items;
    private Integer price;
    private TaskStatus status;
}
