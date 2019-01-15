package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskRequest {
    private String name;
    private Long ownerId;
    private String description;
    private String url;
    private List<TaskItem> items;
    private Double price;
    private TaskStatus status;
}
