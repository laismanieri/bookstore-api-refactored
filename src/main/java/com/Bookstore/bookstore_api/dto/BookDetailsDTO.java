package com.Bookstore.bookstore_api.dto;

import com.Bookstore.bookstore_api.enums.BookType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDetailsDTO {

    private Long id;

    @NotNull(message = "Book type is required")
    private BookType bookType;

    @Positive(message = "Price must be positive")
    private float price;

    private float discountedPrice;

    @Positive(message = "Stock quantity must be positive")
    private int stockQuantity;

    private boolean onSale;

    private boolean featured;

    private Long bookId;
}
