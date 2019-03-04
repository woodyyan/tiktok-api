package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.entity.TaskOrder;
import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.TaskRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public TaskOrders translateToTaskOrders(Page<TaskOrder> orders) {
        List<TaskOrderData> orderList = new ArrayList<>();
        for (TaskOrder order : orders) {
            orderList.add(translateToTaskOrderData(order));
        }
        TaskOrders taskOrders = new TaskOrders();
        taskOrders.setData(orderList);

        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(orders.getNumber());
        meta.setPageSize(orders.getSize());
        meta.setTotalElements(orders.getTotalElements());
        meta.setTotalPages(orders.getTotalPages());
        taskOrders.setMeta(meta);

        taskOrders.setTotalPoints(orderList.stream().map(TaskOrderData::getTask).mapToInt(TaskData::getPointPrice).sum());
        return taskOrders;
    }

    public TaskOrder translateToTaskOrder(TaskOrderRequest taskOrderRequest) {
        TaskOrder order = new TaskOrder();
        order.setStatus(taskOrderRequest.getStatus());
        order.setUserId(taskOrderRequest.getUserId());
        order.setCreatedTime(LocalDateTime.now());
        order.setLastModifiedTime(LocalDateTime.now());
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
        data.setUserId(order.getUserId());
        data.setCreatedTime(order.getCreatedTime().toDateTime());
        data.setLastModifiedTime(order.getLastModifiedTime().toDateTime());
        data.setStatus(order.getStatus());
        data.setTask(taskTranslator.translateToTaskResponse(order.getTask()));
        return data;
    }
}
