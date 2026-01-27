package com.Bookstore.bookstore_api.validator;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class BookDetailsValidator {

    public void validateBookDetails(BookDetailsRequestDTO dto){

        if(dto.getBookType() == null ){
            throw new ValidationException("BookType cannot be empty");
        }

        if (dto.getPrice() <= 0) {
            throw new ValidationException("Page count must be positive");
        }

        if (dto.getStockQuantity() < 0) {
            throw new ValidationException( "Stock quantity cannot be negative");
        }
    }
}
