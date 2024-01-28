package com.example.transactionaltest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long quantity;
    @Column(length = 20)
    private String name;
    private BigDecimal price;
}
