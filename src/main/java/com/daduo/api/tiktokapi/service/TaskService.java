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
import org.joda.time.LocalDateTime;
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
        DeductResult result = deductPoints(taskRequest.getOwnerId(), taskRequest);
        TaskEntity task = translator.translateToTask(taskRequest);
        task.setTotalCredit(result.getCredit());
        task.setTotalPoints(result.getPoints());
        TaskEntity savedTask = repository.save(task);
        return translator.translateToTaskResponse(savedTask);
    }

    public TaskData modifyTask(Long taskId, TaskRequest taskRequest) {
        Optional<TaskEntity> taskEntity = repository.findById(taskId);
        if (taskEntity.isPresent()) {
            TaskEntity task = taskEntity.get();
            task.setLastModifiedTime(LocalDateTime.now());
            if (taskRequest.getDescription() != null) {
                task.setDescription(taskRequest.getDescription());
            }
            if (taskRequest.getItems() != null) {
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
            }
            if (taskRequest.getName() != null) {
                task.setName(taskRequest.getName());
            }
            if (taskRequest.getOwnerId() != null) {
                task.setOwnerId(taskRequest.getOwnerId());
            }
            if (taskRequest.getStatus() != null) {
                task.setStatus(taskRequest.getStatus());
            }
            if (taskRequest.getIsSticky() != null) {
                task.setSticky(taskRequest.getIsSticky());
            }
            if (taskRequest.getCount() != null) {
                task.setCount(taskRequest.getCount());
            }
            if (taskRequest.getIsActive()) {
                task.setActive(taskRequest.getIsActive());
            }
            if (taskRequest.getUrl() != null) {
                task.setUrl(taskRequest.getUrl());
            }
            if (taskRequest.getPlatform() != null) {
                task.setPlatform(taskRequest.getPlatform());
            }
            TaskEntity savedTask = repository.saveAndFlush(task);
            return translator.translateToTaskResponse(savedTask);
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("Task找不到，请确认ID是否正确。");
        }
    }

    private int getPointPrice(List<TaskItem> items, PlatformType platform) {
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

    private class DeductResult {

        private int points;
        private Integer credit;

        void setPoints(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }

        public void setCredit(Integer credit) {
            this.credit = credit;
        }

        public Integer getCredit() {
            return credit;
        }
    }

    private DeductResult deductPoints(Long ownerId, TaskRequest taskRequest) {
        int pointPrice = getPointPrice(taskRequest.getItems(), taskRequest.getPlatform());
        int totalPointPrice = taskRequest.getIsSticky() ? (int) (pointPrice * taskRequest.getCount() * STICKY_PERCENT) : pointPrice * taskRequest.getCount();
        Integer commissionPercent = referenceValueService.searchByName("commissionPercent");
        totalPointPrice = totalPointPrice * ((100 + commissionPercent) / 100);
        CreditData creditData = creditService.getCreditById(ownerId);
        Integer totalCreditPrice = (referenceValueService.searchByName("creditOfPerRmb") / referenceValueService.searchByName("pointsOfPerRmb")) * totalPointPrice;
        totalCreditPrice = (int) (totalCreditPrice * ((100.0 + commissionPercent) / 100.0));
        if (creditData.getPoints() < totalPointPrice && creditData.getCredit() < totalCreditPrice) {
            Error error = new Error();
            error.setTitle("余额不足");
            error.setDetails("余额不足，请充值。");
            error.setStatus("412");
            throw new ErrorException(HttpStatus.OK, error);
        }

        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setUserId(ownerId);
        if (creditData.getPoints() >= totalPointPrice) {
            creditRequest.setPoints(-totalPointPrice);
        } else {
            creditRequest.setCredit(-totalCreditPrice);
        }
        creditService.modifyCredit(creditRequest);
        DeductResult result = new DeductResult();
        result.setPoints(totalPointPrice);
        result.setCredit(totalCreditPrice);
        return result;
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
        TaskOrder order = orderTranslator.translateToTaskOrder(taskOrderRequest);
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

    public TaskOrders searchTaskOrders(Long userId, Pageable page) {
        Page<TaskOrder> orders = orderRepository.findAllByUserId(userId, page);
        return orderTranslator.translateToTaskOrders(orders);
    }
}
