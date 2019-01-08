package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.TaskRequest;
import com.daduo.api.tiktokapi.model.TaskResponse;
import com.daduo.api.tiktokapi.service.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    @ApiOperation("Publish Task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse publishTask(@RequestBody TaskRequest taskRequest) {
        log.info("[START] Publish task with request: {}", taskRequest);
        //TODO 验证数据结构
        TaskResponse taskResponse = service.publishTask(taskRequest);
        log.info("[END] Publish task with response: {}", taskResponse);
        return taskResponse;
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation("Delete Task")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {

    }
}
