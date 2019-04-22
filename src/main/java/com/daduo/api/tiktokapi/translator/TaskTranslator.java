package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.entity.TaskOrder;
import com.daduo.api.tiktokapi.enums.TaskItem;
import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.enums.TaskStatus;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.base.BaseModel;
import com.daduo.api.tiktokapi.repository.AdminRepository;
import com.daduo.api.tiktokapi.repository.TaskOrderRepository;
import com.daduo.api.tiktokapi.service.AccountService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class TaskTranslator {
    @Autowired
    private TaskOrderRepository taskOrderRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AccountService accountService;

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
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setSticky(taskRequest.getIsSticky());
        task.setCount(taskRequest.getCount());
        task.setActive(true);
        task.setUrl(taskRequest.getUrl());
        task.setPlatform(taskRequest.getPlatform());
        return task;
    }

    public Tasks translateToTasks(Page<TaskEntity> entities) {
        Tasks tasks = new Tasks();
        List<TaskData> data = new ArrayList<>();
        List<Admin> admins = adminRepository.findAll();
        List<Long> adminPhones = admins.stream().map(Admin::getPhoneNumber).collect(Collectors.toList());
        for (TaskEntity entity : entities.getContent()) {
            AccountData account = accountService.getAccount(entity.getOwnerId());
            if (adminPhones.contains(account.getPhoneNumber())) {
                entity.setSticky(true);
            }
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

        tasks.setTotalCount(data.stream().mapToInt(TaskData::getCount).sum());
        int completedCount = 0;
        for (Long taskId : data.stream().map(BaseModel::getId).collect(toList())) {
            completedCount += getCompletedCountByTaskId(taskId);
        }
        tasks.setCompletedCount(completedCount);
        tasks.setTotalPoints(data.stream().mapToInt(TaskData::getPointPrice).sum());
        return tasks;
    }

    private Integer getCompletedCountByTaskId(Long taskId) {
        List<TaskOrder> orders = taskOrderRepository.findAllByTaskId(taskId);
        return Math.toIntExact(orders.stream().filter(it -> it.getStatus().equals(TaskOrderStatus.COMPLETED)).count());
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
        data.setCount(taskEntity.getCount());
        data.setOwnerId(taskEntity.getOwnerId());
        data.setStatus(taskEntity.getStatus());
        data.setSticky(taskEntity.isSticky());
        data.setPointPrice(taskEntity.getTotalPoints());
        data.setCreditPrice(taskEntity.getTotalCredit());
        data.setUrl(taskEntity.getUrl());
        data.setPlatform(taskEntity.getPlatform());
        data.setCompletedCount(getCompletedCountByTaskId(taskEntity.getId()));
        data.setActive(taskEntity.isActive());
        data.setLastModifiedTime(taskEntity.getLastModifiedTime().toDateTime());
        return data;
    }
}
