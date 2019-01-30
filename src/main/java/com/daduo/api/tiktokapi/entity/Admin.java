package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.RoleType;
import lombok.Data;

@Data
public class Admin {
    private String username;
    private Long phoneNumber;
    private String password;
    private RoleType role;
}
