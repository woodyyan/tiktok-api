package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.model.TaskRequest;
import com.daduo.api.tiktokapi.model.TaskResponse;
import com.daduo.api.tiktokapi.repository.TaskRepository;
import com.daduo.api.tiktokapi.translator.TaskTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskTranslator translator;

    public TaskResponse publishTask(TaskRequest taskRequest) {
        TaskEntity task = translator.translateToTask(taskRequest);
        TaskEntity savedTask = repository.save(task);
        return translator.translateToTaskResponse(savedTask);
    }
}
