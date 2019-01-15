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
        return translateTaskData(savedTask);
    }

    public TaskEntity translateToTask(TaskRequest taskRequest) {
        TaskEntity task = new TaskEntity();
        LocalDateTime now = LocalDateTime.now();
        task.setCreatedTime(now);
        task.setLastModifiedTime(now);
        task.setDescription(taskRequest.getDescription());
        task.setItems(taskRequest.getItems());
        task.setName(taskRequest.getName());
        task.setOwnerId(taskRequest.getOwnerId());
        task.setPrice(taskRequest.getPrice());
        task.setStatus(taskRequest.getStatus());
        task.setUrl(taskRequest.getUrl());
        return task;
    }

    public Tasks translateToTasks(Page<TaskEntity> entities) {
        Tasks tasks = new Tasks();
        List<TaskData> data = new ArrayList<>();
        for (TaskEntity entity : entities.getContent()) {
            data.add(translateTaskData(entity));
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

    private TaskData translateTaskData(TaskEntity taskEntity) {
        TaskData data = new TaskData();
        data.setCreatedTime(taskEntity.getCreatedTime().toDateTime());
        data.setDescription(taskEntity.getDescription());
        data.setId(taskEntity.getId());
        data.setItems(taskEntity.getItems());
        data.setName(taskEntity.getName());
        data.setPrice(taskEntity.getPrice());
        data.setOwnerId(taskEntity.getOwnerId());
        data.setStatus(taskEntity.getStatus());
        data.setUrl(taskEntity.getUrl());
        data.setLastModifiedTime(taskEntity.getLastModifiedTime().toDateTime());
        return data;
    }
}
