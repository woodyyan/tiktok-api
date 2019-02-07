package com.daduo.api.tiktokapi.entity;

import lombok.Data;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_order")
public class ProductOrder {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}
