package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.entity.TaskOrder;
import com.daduo.api.tiktokapi.enums.PlatformType;
import com.daduo.api.tiktokapi.enums.TaskItem;
import com.daduo.api.tiktokapi.enums.TaskOrderStatus;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.image.ImageMatchService;
import com.daduo.api.tiktokapi.image.OCRService;
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

    @Autowired
    private TaskRepository taskRepository;

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

    public TaskStatistics getTaskStatistics() {
        TaskStatistics statistics = new TaskStatistics();
        DouyinData douyin = new DouyinData();
        douyin.setLikeCount(5.0);
        douyin.setFollowCount(20.0);
        douyin.setCommentCount(36.0);
        douyin.setLikeFollowCount(10.0);
        douyin.setLikeFollowCommentCount(10.0);
        douyin.setPlayCount(20.0);
        statistics.setDouyinData(douyin);
        KuaishouData kuaishou = new KuaishouData();
        kuaishou.setLikeCount(5.0);
        kuaishou.setFollowCount(20.0);
        kuaishou.setCommentCount(36.0);
        kuaishou.setLikeFollowCount(10.0);
        kuaishou.setLikeFollowCommentCount(10.0);
        kuaishou.setPlayCount(20.0);
        statistics.setKuaishouData(kuaishou);
        return statistics;
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
        List<TaskOrder> existingOrders = orderRepository.findAllByUserIdAndTaskId(taskOrderRequest.getUserId(), taskOrderRequest.getTaskId());
        TaskOrderResponse response = new TaskOrderResponse();
        if (existingOrders.size() == 0) {
            Optional<TaskEntity> task = taskRepository.findById(taskOrderRequest.getTaskId());
            if (task.isPresent()) {
                boolean isSuccess = verifyTask(taskOrderRequest, task.get());

                TaskOrder order = orderTranslator.translateToTaskOrder(taskOrderRequest);
                if (isSuccess) {
                    order.setStatus(TaskOrderStatus.COMPLETED);
                    addPoints(taskOrderRequest.getUserId(), task.get());
                    response.setMessage("任务验证成功。");
                } else {
                    order.setStatus(TaskOrderStatus.FAILED);
                    response.setMessage("任务验证失败。");
                }
                TaskOrder save = orderRepository.saveAndFlush(order);
                TaskOrderData data = orderTranslator.translateToTaskOrderData(save);
                response.setData(data);
            }
        } else {
            response.setMessage("同一任务只能完成一次。");
        }
        return response;
    }

    private boolean verifyTask(TaskOrderRequest taskOrderRequest, TaskEntity task) {
        boolean playSuccess = false;
        boolean commentSuccess = false;
        boolean likeSuccess = false;
        boolean followSuccess = false;
        if (task.isNeedPlay()) {
            playSuccess = true;
        }
        if (task.isNeedLike()) {
            likeSuccess = ImageMatchService.verifyTask(taskOrderRequest.getLikeImage(), ImageMatchService.VerifyType.LIKE);
        }
        if (task.isNeedFollow()) {
            followSuccess = ImageMatchService.verifyTask(taskOrderRequest.getFollowImage(), ImageMatchService.VerifyType.FOLLOW);
        }
        if (task.isNeedComment()) {
            commentSuccess = OCRService.verifyTask(taskOrderRequest.getCommentImage());
        }
        return playSuccess | commentSuccess | likeSuccess | followSuccess;
    }

    private void addPoints(Long userId, TaskEntity task) {
        CreditRequest request = new CreditRequest();
        request.setUserId(userId);
        Integer points = task.getTotalPoints();
        request.setPoints(points);
        creditService.modifyCredit(request);
    }

    public TaskOrders searchTaskOrders(Long userId, Pageable page) {
        Page<TaskOrder> orders = orderRepository.findAllByUserId(userId, page);
        return orderTranslator.translateToTaskOrders(orders);
    }
}
