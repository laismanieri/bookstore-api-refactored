package com.Bookstore.bookstore_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;

@Data
public class BookDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    @NotNull(message = "Publication year is required")
    @Positive(message = "Publication year must be positive")
    private int publicationYear;

    @NotBlank(message = "Synopsis cannot be empty")
    @Size(max = 2000, message = "Synopsis cannot exceed 2000 characters")
    private String synopsis;

    private String genre;

    @NotBlank(message = "Publisher cannot be empty")
    private String publisher;

    @Positive(message = "Page count must be positive")
    private int pageCount;

    private String imageUrl;

    @Valid
    private List<BookDetailsDTO> details;
}
