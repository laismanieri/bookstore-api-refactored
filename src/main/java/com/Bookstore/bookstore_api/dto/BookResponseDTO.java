package com.Bookstore.bookstore_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDTO {

    private Long id;

    private String title;

    private String author;

    private int publicationYear;

    private String synopsis;

    private String genre;

    private String publisher;

    private int pageCount;

    private String imageUrl;

    private List<BookDetailsResponseDTO> details;
}
