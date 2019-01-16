package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.service.TaskService;
import com.daduo.api.tiktokapi.validator.TaskValidator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@Slf4j
@Api(tags = "任务接口", description = "任务相关的操作")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskValidator validator;

    @PostMapping
    @ApiOperation("发布任务")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse publishTask(@RequestBody @ApiParam(value = "任务Json") TaskRequest taskRequest) {
        log.info("[START] Publish task with request: {}", taskRequest);
        validator.validate(taskRequest);
        TaskData taskData = service.publishTask(taskRequest);
        log.info("[END] Publish task with response: {}", taskData);
        TaskResponse response = new TaskResponse();
        response.setData(taskData);
        return response;
    }

    @GetMapping("/{taskId}")
    @ApiOperation("获取任务")
    public TaskResponse getTask(@PathVariable @ApiParam(value = "任务ID") Long taskId) {
        log.info("[START] Get task with taskId: {}", taskId);
        TaskData task = service.getTask(taskId);
        log.info("[END] Get task with response: {}", task);
        TaskResponse response = new TaskResponse();
        response.setData(task);
        return response;
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation("删除任务")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable @ApiParam(value = "任务ID") Long taskId) {
        log.info("[START] Delete task with taskId: {}", taskId);
        service.deleteTask(taskId);
    }

    @GetMapping
    @ApiOperation("搜索任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public Tasks searchTasks(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
            @ApiParam(value = "分页")
                                     Pageable page) {
        log.info("[START] search tasks with page: {}", page);
        Tasks tasks = service.searchTasks(page);
        log.info("[END] search tasks with response: {}", tasks);
        return tasks;
    }

    @PostMapping("/verify")
    @ApiOperation("验证任务")
    public VerifyTaskResponse verifyTask(@RequestBody @ApiParam(value = "验证任务请求Json") VerifyTaskRequest verifyTaskRequest) {
        return service.verifyTask(verifyTaskRequest);
    }
}
