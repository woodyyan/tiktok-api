package com.daduo.api.tiktokapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private Long id;
    private String username;
    private Long phoneNumber;
    private String password;
    private String wechatId;
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;
}
