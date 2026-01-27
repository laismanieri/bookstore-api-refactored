package com.Bookstore.bookstore_api.validator;

import com.Bookstore.bookstore_api.dto.BookRequestDTO;
import com.Bookstore.bookstore_api.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class BookValidator {

    public void validateBook(BookRequestDTO dto) {

        String synopsis = dto.getSynopsis();
        int currentYear = Year.now().getValue();

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new ValidationException("Title cannot be empty");
        }
        if (dto.getAuthor() == null || dto.getAuthor().isBlank()) {
            throw new ValidationException("Author cannot be empty");
        }
        if (dto.getPublisher() == null || dto.getPublisher().isBlank()) {
            throw new ValidationException("Publisher cannot be empty");
        }
        if (dto.getPublicationYear() <= 0){
            throw new ValidationException("Publication year cannot be empty e must be positive ");
        }
        if (dto.getPageCount() <= 0) {
            throw new ValidationException("Page count must be positive");
        }

        if (synopsis == null || synopsis.trim().isEmpty() || synopsis.trim().length() > 2000) {
            throw new ValidationException("Synopsis cannot be empty or exceed 2000 characters");
        }

        if (dto.getPublicationYear() > currentYear) {
            throw new ValidationException("Publication year cannot be in the future");
        }
    }
}
