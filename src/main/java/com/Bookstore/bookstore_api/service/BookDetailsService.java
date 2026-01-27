package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.repository.BookDetailsRepository;
import com.Bookstore.bookstore_api.repository.BookRepository;
import com.Bookstore.bookstore_api.validator.BookDetailsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookDetailsValidator bookDetailsValidator;
    private final BookRepository bookRepository;

    @Transactional
    public BookDetailsEntity addNewBookDetails(BookDetailsResponseDTO dto){

        bookDetailsValidator.validateBookDetails(dto);

        BookEntity book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        BookDetailsEntity newBookDetails = new BookDetailsEntity();
        newBookDetails.setGuid(UUID.randomUUID().toString());
        newBookDetails.setBookType(dto.getBookType());
        newBookDetails.setPrice(dto.getPrice());
        newBookDetails.setStockQuantity(dto.getStockQuantity());
        newBookDetails.setOnSale(dto.isOnSale());
        newBookDetails.setFeatured(dto.isFeatured());

//        if (newBookDetails.getOnSale().equals(Boolean.TRUE){
//            float discountedPrice = (dto.getPrice() * 0.9F);
//            dto.setDiscountedPrice(discountedPrice);
//        } else dto.setDiscountedPrice(0);

        log.debug("Adding a new bookdetail [GUID = {}]", newBookDetails.getGuid());

        return bookDetailsRepository.save(newBookDetails);
    }
}
