package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class VerifyTaskRequest {
    private Long taskId;
    private String imageUrl;
    private Long userId;
}
