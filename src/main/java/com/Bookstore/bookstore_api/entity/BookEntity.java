package com.Bookstore.bookstore_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = { @Index(name = "IDX_GUID_BOOK", columnList = "GUID")})
public class BookEntity extends BaseEntity {

    private String title;

    private String author;

    private int publicationYear;

    @Lob
    @Column(name = "synopsis", columnDefinition = "LONGTEXT")
    private String synopsis;

    private String genre;

    private String publisher;

    private int pageCount;

    private String imageUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookDetailsEntity> details;
}
