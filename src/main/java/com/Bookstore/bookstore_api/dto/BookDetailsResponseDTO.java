package com.Bookstore.bookstore_api.dto;

import com.Bookstore.bookstore_api.enums.BookType;
import lombok.Data;

@Data
public class BookDetailsResponseDTO {

    private BookType bookType;

    private float price;

    private float discountedPrice;

    private int stockQuantity;

    private boolean onSale;

    private boolean featured;

    private Long bookId;
}
