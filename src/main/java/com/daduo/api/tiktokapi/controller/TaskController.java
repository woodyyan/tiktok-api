package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.TaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @PostMapping
    @ApiOperation("Publish Task")
    @ResponseStatus(HttpStatus.CREATED)
    public void publishTask(@RequestBody TaskRequest taskRequest) {

    }

    @DeleteMapping("/{taskId}")
    @ApiOperation("Delete Task")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {

    }
}
