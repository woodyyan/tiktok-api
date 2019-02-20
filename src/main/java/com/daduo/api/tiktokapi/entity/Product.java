package com.daduo.api.tiktokapi.entity;

import lombok.Data;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}
