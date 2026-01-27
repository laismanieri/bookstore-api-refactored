package com.Bookstore.bookstore_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(nullable = false, unique = true, updatable = false)
    private String guid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
