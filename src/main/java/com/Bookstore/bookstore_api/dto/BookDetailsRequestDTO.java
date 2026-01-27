package com.Bookstore.bookstore_api.dto;

import com.Bookstore.bookstore_api.enums.BookType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDetailsRequestDTO {

    @NotNull(message = "Book type is required")
    private BookType bookType;

    @Positive(message = "Price must be positive")
    private float price;

    @Positive(message = "Stock quantity must be positive")
    private int stockQuantity;

    private boolean onSale;

    private boolean featured;
}
