package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.entity.TaskOrder;
import com.daduo.api.tiktokapi.enums.PlatformType;
import com.daduo.api.tiktokapi.enums.TaskItem;
import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.TaskOrderRepository;
import com.daduo.api.tiktokapi.repository.TaskRepository;
import com.daduo.api.tiktokapi.translator.TaskOrderTranslator;
import com.daduo.api.tiktokapi.translator.TaskTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private static final double STICKY_PERCENT = 1.2;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskTranslator translator;

    @Autowired
    private TaskOrderRepository orderRepository;

    @Autowired
    private TaskOrderTranslator orderTranslator;

    @Autowired
    private ReferenceValueService referenceValueService;

    @Autowired
    private CreditService creditService;

    public TaskData publishTask(TaskRequest taskRequest) {
        double pointPrice = getPointPrice(taskRequest.getItems(), taskRequest.getPlatform());
        double totalPrice = taskRequest.isSticky() ? pointPrice * taskRequest.getCount() * STICKY_PERCENT : pointPrice * taskRequest.getCount();
        deductPoints(taskRequest.getOwnerId(), totalPrice);
        TaskEntity task = translator.translateToTask(taskRequest);
        TaskEntity savedTask = repository.save(task);
        return translator.translateToTaskResponse(savedTask);
    }

    private double getPointPrice(List<TaskItem> items, PlatformType platform) {
        if (platform == PlatformType.DOUYIN) {
            if (items.size() == 1 && items.get(0) == TaskItem.CLICK_RATE) {
                return referenceValueService.searchByName("pointsOfPerTiktokPlay");
            } else if (items.size() == 1 && items.get(0) == TaskItem.COMMENT) {
                return referenceValueService.searchByName("pointsOfPerTiktokComment");
            } else if (items.size() == 1 && items.get(0) == TaskItem.FOLLOW) {
                return referenceValueService.searchByName("pointsOfPerTiktokFollow");
            } else if (items.size() == 1 && items.get(0) == TaskItem.LIKE) {
                return referenceValueService.searchByName("pointsOfPerTiktokLike");
            } else if (items.size() == 2 && items.contains(TaskItem.LIKE) && items.contains(TaskItem.FOLLOW)) {
                return referenceValueService.searchByName("pointsOfPerTiktokLikeAndFollow");
            } else if (items.size() == 3 && items.contains(TaskItem.COMMENT)) {
                return referenceValueService.searchByName("pointsOfPerTiktokLikeAndFollowAndComment");
            }
        } else {
            if (items.size() == 1 && items.get(0) == TaskItem.CLICK_RATE) {
                return referenceValueService.searchByName("pointsOfPerKuaishouPlay");
            } else if (items.size() == 1 && items.get(0) == TaskItem.COMMENT) {
                return referenceValueService.searchByName("pointsOfPerKuaishouComment");
            } else if (items.size() == 1 && items.get(0) == TaskItem.FOLLOW) {
                return referenceValueService.searchByName("pointsOfPerKuaishouFollow");
            } else if (items.size() == 1 && items.get(0) == TaskItem.LIKE) {
                return referenceValueService.searchByName("pointsOfPerKuaishouLike");
            } else if (items.size() == 2 && items.contains(TaskItem.LIKE) && items.contains(TaskItem.FOLLOW)) {
                return referenceValueService.searchByName("pointsOfPerKuaishouLikeAndFollow");
            } else if (items.size() == 3 && items.contains(TaskItem.COMMENT)) {
                return referenceValueService.searchByName("pointsOfPerKuaishouLikeAndFollowAndComment");
            }
        }
        return 0;
    }


    private void deductPoints(Long ownerId, Double totalPointPrice) {
        CreditData creditData = creditService.getCreditById(ownerId);
        double totalCreditPrice = (referenceValueService.searchByName("creditOfPerRmb") / referenceValueService.searchByName("pointsOfPerRmb")) * totalPointPrice;
        if (creditData.getPoints() < totalPointPrice && creditData.getCredit() < totalCreditPrice) {
            Error error = new Error();
            error.setTitle("余额不足");
            error.setDetails("余额不足，请充值。");
            error.setStatus(HttpStatus.PRECONDITION_FAILED.toString());
            throw new ErrorException(HttpStatus.PRECONDITION_FAILED, error);
        }

        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setUserId(ownerId);
        if (creditData.getPoints() >= totalPointPrice) {
            creditRequest.setPoints(-totalPointPrice);
        } else {
            creditRequest.setCredit(-totalCreditPrice);
        }
        creditService.modifyCredit(creditRequest);
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
