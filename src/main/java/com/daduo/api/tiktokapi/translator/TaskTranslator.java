package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.enums.TaskItem;
import com.daduo.api.tiktokapi.enums.TaskStatus;
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
        for (TaskItem item : taskRequest.getItems()) {
            if (item == TaskItem.CLICK_RATE) {
                task.setNeedPlay(true);
            } else if (item == TaskItem.COMMENT) {
                task.setNeedComment(true);
            } else if (item == TaskItem.FOLLOW) {
                task.setNeedFollow(true);
            } else if (item == TaskItem.LIKE) {
                task.setNeedLike(true);
            }
        }
        task.setName(taskRequest.getName());
        task.setOwnerId(taskRequest.getOwnerId());
        task.setPointPrice(taskRequest.getCreditPrice());
        task.setCreditPrice(taskRequest.getPointPrice());
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setSticky(taskRequest.isSticky());
        task.setCount(taskRequest.getCount());
        task.setActive(true);
        task.setUrl(taskRequest.getUrl());
        task.setPlatform(taskRequest.getPlatform());
        return task;
    }

    public Tasks translateToTasks(Page<TaskEntity> entities) {
        Tasks tasks = new Tasks();
        List<TaskData> data = new ArrayList<>();
        for (TaskEntity entity : entities.getContent()) {
            if (entity.isSticky()) {
                data.add(0, translateTaskData(entity));
            } else {
                data.add(translateTaskData(entity));
            }
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
        if (taskEntity.isNeedComment()) {
            data.getItems().add(TaskItem.COMMENT);
        }
        if (taskEntity.isNeedFollow()) {
            data.getItems().add(TaskItem.FOLLOW);
        }
        if (taskEntity.isNeedLike()) {
            data.getItems().add(TaskItem.LIKE);
        }
        if (taskEntity.isNeedPlay()) {
            data.getItems().add(TaskItem.CLICK_RATE);
        }
        data.setName(taskEntity.getName());
        data.setPointPrice(taskEntity.getPointPrice());
        data.setCreditPrice(taskEntity.getCreditPrice());
        data.setCount(taskEntity.getCount());
        data.setOwnerId(taskEntity.getOwnerId());
        data.setStatus(taskEntity.getStatus());
        data.setSticky(taskEntity.isSticky());
        data.setUrl(taskEntity.getUrl());
        data.setPlatform(taskEntity.getPlatform());
        data.setActive(taskEntity.isActive());
        data.setLastModifiedTime(taskEntity.getLastModifiedTime().toDateTime());
        return data;
    }
}
