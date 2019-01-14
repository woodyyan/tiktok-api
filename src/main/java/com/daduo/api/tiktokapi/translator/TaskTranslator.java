package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.model.PagingMeta;
import com.daduo.api.tiktokapi.model.TaskData;
import com.daduo.api.tiktokapi.model.TaskRequest;
import com.daduo.api.tiktokapi.model.Tasks;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskTranslator {
    public TaskData translateToTaskResponse(TaskEntity savedTask) {
        TaskData response = new TaskData();
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

    public Tasks translateToTasks(Page<TaskEntity> entities) {
        Tasks tasks = new Tasks();
        List<TaskData> data = new ArrayList<>();
        for (TaskEntity entity : entities.getContent()) {
            TaskData task = new TaskData();
            task.setCreatedTime(entity.getCreatedTime().toDateTime());
            task.setDescription(entity.getDescription());
            task.setId(entity.getId());
            task.setItems(entity.getItems());
            task.setLastModifiedTime(entity.getLastModifiedTime().toDateTime());
            task.setName(entity.getName());
            task.setPrice(entity.getPrice());
            task.setStatus(entity.getStatus());
            task.setUrl(entity.getUrl());
            data.add(task);
        }
        tasks.setData(data);
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(entities.getNumber());
        meta.setPageSize(entities.getSize());
        meta.setTotalElements(entities.getTotalElements());
        meta.setTotalPages(entities.getTotalPages());
        tasks.setMeta(meta);
        return tasks;
    }
}
