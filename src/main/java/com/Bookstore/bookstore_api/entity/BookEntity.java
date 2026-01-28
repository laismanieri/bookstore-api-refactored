package com.Bookstore.bookstore_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "books", indexes = { @Index(name = "IDX_GUID_BOOK", columnList = "GUID")})
public class BookEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int publicationYear;

    @Lob
    @Column(name = "synopsis", columnDefinition = "LONGTEXT", nullable = false)
    private String synopsis;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private int pageCount;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookDetailsEntity> details;
}
