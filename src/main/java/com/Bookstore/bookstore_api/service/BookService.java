package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookRequestDTO;
import com.Bookstore.bookstore_api.dto.BookResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.exceptions.DomainValidationException;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookRepository;
import com.Bookstore.bookstore_api.validator.BookValidator;
import jakarta.validation.Valid;
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
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public List<BookResponseDTO> listAllBooks() {

        log.info("Listing all books");

        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookResponseDTO getBookByGuid(String guid) {

        log.info("Finding book by ID: {}", guid);

        BookEntity book = bookRepository.findByGuid(guid)
                .orElseThrow(() -> new DomainValidationException("Book not found: " + guid));
        return bookMapper.toResponse(book);
    }
    @Transactional
    public BookResponseDTO addNewBook(@Valid BookRequestDTO dto) {
        log.info("Creating book: {}", dto.getTitle());

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

        BookEntity saved = bookRepository.save(newBook);
        log.debug("Book created successfully: ID={}, GUID={}", saved.getId(), saved.getGuid());

        return bookMapper.toResponse(saved);
    }

    @Transactional
    public BookResponseDTO updateBook(String guid, @Valid BookRequestDTO dto) {

        log.info("Updating book ID: {}", guid);

        BookEntity existingBook = bookRepository.findByGuid(guid)
                .orElseThrow(() -> new DomainValidationException("Book not found: " + guid));

        existingBook.setTitle(dto.getTitle());
        existingBook.setAuthor(dto.getAuthor());
        existingBook.setPublisher(dto.getPublisher());
        existingBook.setPublicationYear(dto.getPublicationYear());
        existingBook.setPageCount(dto.getPageCount());
        existingBook.setSynopsis(dto.getSynopsis());
        existingBook.setGenre(dto.getGenre());
        existingBook.setImageUrl(dto.getImageUrl());

        BookEntity savedBook = bookRepository.save(existingBook);

        log.debug("Updating book [ Guid = {}, Title = {}]", guid, existingBook.getTitle());

        return bookMapper.toResponse(savedBook);
    }

    @Transactional
    public void deleteBook(String guid){

        log.info("Deleting book GUID: {}", guid);

        BookEntity existingBook = bookRepository.findByGuid(guid).orElseThrow(
                () -> new DomainValidationException("Book GUID not found: " + guid));

        log.debug("Deleting book [ GUID = {} ]", guid);

        bookRepository.delete(existingBook);
    }
}
