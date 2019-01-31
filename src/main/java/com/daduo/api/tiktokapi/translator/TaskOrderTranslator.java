package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.entity.TaskOrder;
import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.model.TaskOrderData;
import com.daduo.api.tiktokapi.model.TaskOrderRequest;
import com.daduo.api.tiktokapi.model.TaskOrders;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskOrderTranslator {

    @Autowired
    private TaskTranslator taskTranslator;

    @Autowired
    private TaskRepository repository;

    public TaskOrders translateToTaskOrders(List<TaskOrder> orders) {
        List<TaskOrderData> orderList = new ArrayList<>();
        for (TaskOrder order : orders) {
            orderList.add(translateToTaskOrderData(order));
        }
        TaskOrders taskOrders = new TaskOrders();
        taskOrders.setData(orderList);
        return taskOrders;
    }

    public TaskOrder translateToTaskOrder(TaskOrderRequest taskOrderRequest, TaskOrderStatus status) {
        TaskOrder order = new TaskOrder();
        order.setStatus(status);
        order.setImageUrl(taskOrderRequest.getImageUrl());
        order.setUserId(taskOrderRequest.getUserId());
        Optional<TaskEntity> task = repository.findById(taskOrderRequest.getTaskId());
        if (task.isPresent()) {
            order.setTask(task.get());
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("任务ID找不到。");
        }
        return order;
    }

    public TaskOrderData translateToTaskOrderData(TaskOrder order) {
        TaskOrderData data = new TaskOrderData();
        data.setId(order.getId());
        data.setImageUrl(order.getImageUrl());
        data.setUserId(order.getUserId());
        data.setStatus(order.getStatus());
        data.setTask(taskTranslator.translateToTaskResponse(order.getTask()));
        return data;
    }
}
