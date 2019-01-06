package com.daduo.api.tiktokapi.entity;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String username;
    private Long phoneNumber;
    private String password;
    private String wechatId;
}
