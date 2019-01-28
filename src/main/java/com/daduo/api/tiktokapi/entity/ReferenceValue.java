package com.daduo.api.tiktokapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "reference_value")
public class ReferenceValue {
    @Id
    @Column(name = "rmb", nullable = false)
    private Long rmb;
    @Column(name = "points", nullable = false)
    private Long points;
    @Column(name = "credit", nullable = false)
    private Long credit;
}
