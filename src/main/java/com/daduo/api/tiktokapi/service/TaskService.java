package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.TaskRequest;
import com.daduo.api.tiktokapi.model.TaskData;
import com.daduo.api.tiktokapi.model.Tasks;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.TaskRepository;
import com.daduo.api.tiktokapi.translator.TaskTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskTranslator translator;

    public TaskData publishTask(TaskRequest taskRequest) {
        TaskEntity task = translator.translateToTask(taskRequest);
        TaskEntity savedTask = repository.save(task);
        return translator.translateToTaskResponse(savedTask);
    }

    public void deleteTask(Long taskId) {
        Optional<TaskEntity> task = repository.findById(taskId);
        if (task.isPresent()) {
            repository.delete(task.get());
        } else {
            Error error = new Error();
            error.setStatus("404");
            error.setDetails("Task找不到，请确认ID是否正确。");
            error.setTitle("Task找不到");
            throw new ErrorException(HttpStatus.NOT_FOUND, error);
        }
    }

    public Tasks searchTasks(Pageable page) {
        Page<TaskEntity> entities = repository.findAll(page);
        return translator.translateToTasks(entities);
    }
}
