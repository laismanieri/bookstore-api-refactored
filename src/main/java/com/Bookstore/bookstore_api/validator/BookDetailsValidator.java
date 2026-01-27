package com.Bookstore.bookstore_api.validator;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class BookDetailsValidator {
    public void validateBookDetails(BookDetailsRequestDTO dto) {
        if (dto.getBookType() == null) {
            throw new ValidationException("BookType è obbligatorio");
        }
        if (dto.getPrice() <= 0) {
            throw new ValidationException("Prezzo deve essere positivo");
        }
        if (dto.getStockQuantity() < 0) {
            throw new ValidationException("Quantità stock non può essere negativa");
        }
    }
}
