package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.RoleType;
import lombok.Data;

import java.util.List;

@Data
public class Authority {

    private Long id;

    private RoleType name;

    private List<Admin> admins;
}