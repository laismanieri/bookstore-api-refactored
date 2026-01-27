package com.Bookstore.bookstore_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
