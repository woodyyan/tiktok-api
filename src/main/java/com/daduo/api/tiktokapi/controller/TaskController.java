package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.service.TaskService;
import com.daduo.api.tiktokapi.validator.TaskValidator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskValidator validator;

    @PostMapping
    @ApiOperation("Publish Task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse publishTask(@RequestBody TaskRequest taskRequest) {
        log.info("[START] Publish task with request: {}", taskRequest);
        validator.validate(taskRequest);
        TaskData taskData = service.publishTask(taskRequest);
        log.info("[END] Publish task with response: {}", taskData);
        TaskResponse response = new TaskResponse();
        response.setData(taskData);
        return response;
    }

    @GetMapping("/{taskId}")
    @ApiOperation("Get Task")
    public TaskResponse getTask(@PathVariable Long taskId) {
        log.info("[START] Get task with taskId: {}", taskId);
        TaskData task = service.getTask(taskId);
        log.info("[END] Get task with response: {}", task);
        TaskResponse response = new TaskResponse();
        response.setData(task);
        return response;
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation("Delete Task")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        log.info("[START] Delete task with taskId: {}", taskId);
        service.deleteTask(taskId);
    }

    @GetMapping
    @ApiOperation("Search Task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "The index of the page requested",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "The number of elements per page",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public Tasks searchTasks(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                     Pageable page) {
        log.info("[START] search tasks with page: {}", page);
        Tasks tasks = service.searchTasks(page);
        log.info("[END] search tasks with response: {}", tasks);
        return tasks;
    }

    @PostMapping("/verify")
    @ApiOperation("Verify Task")
    public VerifyTaskResponse verifyTask(@RequestBody VerifyTaskRequest verifyTaskRequest) {
        return service.verifyTask(verifyTaskRequest);
    }
}
