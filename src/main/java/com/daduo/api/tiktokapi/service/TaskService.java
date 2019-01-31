package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.entity.TaskOrder;
import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.repository.TaskOrderRepository;
import com.daduo.api.tiktokapi.repository.TaskRepository;
import com.daduo.api.tiktokapi.translator.TaskOrderTranslator;
import com.daduo.api.tiktokapi.translator.TaskTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskTranslator translator;

    @Autowired
    private TaskOrderRepository orderRepository;

    @Autowired
    private TaskOrderTranslator orderTranslator;

    public TaskData publishTask(TaskRequest taskRequest) {
        TaskEntity task = translator.translateToTask(taskRequest);
        TaskEntity savedTask = repository.save(task);
        return translator.translateToTaskResponse(savedTask);
    }

    public void deleteTask(Long taskId) {
        Optional<TaskEntity> task = repository.findById(taskId);
        if (task.isPresent()) {
            repository.delete(task.get());
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("Task找不到，请确认ID是否正确。");
        }
    }

    public Tasks searchTasks(Long userId, Pageable page) {
        Page<TaskEntity> entities;
        if (userId == null) {
            entities = repository.findAll(page);
        } else {
            entities = repository.findAllByOwnerId(userId, page);
        }
        return translator.translateToTasks(entities);
    }

    public TaskData getTask(Long taskId) {
        Optional<TaskEntity> entity = repository.findById(taskId);
        if (entity.isPresent()) {
            return translator.translateToTaskResponse(entity.get());
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("Task找不到，请确认ID是否正确。");
        }
    }

    public TaskOrderResponse createTaskOrder(TaskOrderRequest taskOrderRequest) {
        //TODO 先验证任务成功与否，再保存
        TaskOrder order = orderTranslator.translateToTaskOrder(taskOrderRequest, TaskOrderStatus.COMPLETED);
        TaskOrder save = orderRepository.saveAndFlush(order);
        TaskOrderData data = orderTranslator.translateToTaskOrderData(save);
        TaskOrderResponse response = new TaskOrderResponse();
        response.setData(data);
        if (data.getStatus() == TaskOrderStatus.COMPLETED) {
            response.setMessage("任务验证成功。");
        } else {
            response.setMessage("任务验证失败。");
        }
        return response;
    }

    public TaskOrders searchTaskOrders(Long userId) {
        List<TaskOrder> orders = orderRepository.findAllByUserId(userId);
        return orderTranslator.translateToTaskOrders(orders);
    }
}
