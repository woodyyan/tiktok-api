package com.daduo.api.tiktokapi.entity;

import com.daduo.api.tiktokapi.enums.RoleType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Admin {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    private RoleType role;
}
