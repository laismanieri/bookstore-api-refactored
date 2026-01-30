package com.Bookstore.bookstore_api.entity;

import com.Bookstore.bookstore_api.enums.BookType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = { @Index(name = "IDX_GUID_BOOKDETAILS", columnList = "GUID")})
public class BookDetailsEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private BookType bookType;

    private float price;

    private int stockQuantity;

    private boolean onSale;

    private boolean featured;

    @JsonBackReference
    @ManyToOne
    private BookEntity book;
}
