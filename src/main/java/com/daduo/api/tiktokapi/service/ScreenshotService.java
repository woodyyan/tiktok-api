package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ScreenshotStatus;
import com.daduo.api.tiktokapi.model.ScreenshotRequest;
import com.daduo.api.tiktokapi.repository.ScreenshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreenshotService {
    @Autowired
    private ScreenshotRepository repository;

    public void saveScreenshotStatus(ScreenshotRequest request) {
        ScreenshotStatus screenshot = new ScreenshotStatus();
        screenshot.setUserId(request.getUserId());
        screenshot.setTaskId(request.getTaskId());
        screenshot.setStatus(request.isStatus());
        repository.save(screenshot);
    }
}
