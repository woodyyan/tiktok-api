package com.daduo.api.tiktokapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "service_contact")
public class ServiceContactEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wechat")
    private String wechat;

    @Column(name = "qq")
    private String qq;
}
