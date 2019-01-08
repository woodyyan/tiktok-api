package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.model.TaskRequest;
import com.daduo.api.tiktokapi.model.TaskResponse;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class TaskTranslator {
    public TaskResponse translateToTaskResponse(TaskEntity savedTask) {
        TaskResponse response = new TaskResponse();
        response.setCreatedTime(savedTask.getCreatedTime().toDateTime());
        response.setDescription(savedTask.getDescription());
        response.setId(savedTask.getId());
        response.setItems(savedTask.getItems());
        response.setName(savedTask.getName());
        response.setPrice(savedTask.getPrice());
        response.setStatus(savedTask.getStatus());
        response.setUrl(savedTask.getUrl());
        response.setLastModifiedTime(savedTask.getLastModifiedTime().toDateTime());
        return response;
    }

    public TaskEntity translateToTask(TaskRequest taskRequest) {
        TaskEntity task = new TaskEntity();
        LocalDateTime now = LocalDateTime.now();
        task.setCreatedTime(now);
        task.setLastModifiedTime(now);
        task.setDescription(taskRequest.getDescription());
        task.setItems(taskRequest.getItems());
        task.setName(taskRequest.getName());
        task.setPrice(taskRequest.getPrice());
        task.setStatus(taskRequest.getStatus());
        task.setUrl(taskRequest.getUrl());
        return task;
    }
}
