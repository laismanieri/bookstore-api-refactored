package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.repository.BookRepository;
import com.Bookstore.bookstore_api.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    @Transactional
    public BookEntity addNewBook(BookRequestDTO dto) {

        bookValidator.validateBook(dto);

        BookEntity newBook = new BookEntity();
        newBook.setGuid(UUID.randomUUID().toString());
        newBook.setTitle(dto.getTitle());
        newBook.setAuthor(dto.getAuthor());
        newBook.setPublisher(dto.getPublisher());
        newBook.setPublicationYear(dto.getPublicationYear());
        newBook.setPageCount(dto.getPageCount());
        newBook.setSynopsis(dto.getSynopsis());
        newBook.setGenre(dto.getGenre());
        newBook.setImageUrl(dto.getImageUrl());

        log.debug("Adding a new book [GUID = {}, Title = {}]", newBook.getGuid(), newBook.getTitle());

        if (dto.getDetails() != null) {
            List<BookDetailsEntity> detailsEntities = dto.getDetails().stream()
                    .map(detailDto -> {
                        BookDetailsEntity newBookDetails = new BookDetailsEntity();
                        newBookDetails.setGuid(UUID.randomUUID().toString());
                        newBookDetails.setBookType(detailDto.getBookType());
                        newBookDetails.setPrice(detailDto.getPrice());
                        newBookDetails.setStockQuantity(detailDto.getStockQuantity());
                        newBookDetails.setOnSale(detailDto.isOnSale());
                        newBookDetails.setFeatured(detailDto.isFeatured());
                        newBookDetails.setBook(newBook);

                        return newBookDetails;

                    })
                    .toList();
            newBook.setDetails(detailsEntities);
        }

        log.debug("Adding a new book [GUID = {}, Title = {}]", newBook.getGuid(), newBook.getTitle());
        return bookRepository.save(newBook);
    }

}
