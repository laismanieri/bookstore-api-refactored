package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.exceptions.DomainValidationException;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookDetailsRepository;
import com.Bookstore.bookstore_api.repository.BookRepository;
import com.Bookstore.bookstore_api.validator.BookDetailsValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    private final BookDetailsValidator bookDetailsValidator;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public List<BookDetailsResponseDTO> listAllBookDetail() {

        log.info("Listing all details");

        return bookDetailsRepository.findAll()
                .stream()
                .map(bookMapper::toDetailResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDetailsResponseDTO getBookDetailByGuid(String guid) {

        log.info("Finding detail by GUID: {}", guid);

        BookDetailsEntity bookDetail = bookDetailsRepository.findByGuid(guid)
                .orElseThrow(() -> new DomainValidationException("Details not found: " + guid));
        return bookMapper.toDetailResponse(bookDetail);
    }

    @Transactional
        public BookDetailsResponseDTO createBookDetail(@Valid BookDetailsRequestDTO dto, String guid) {

        log.info("Creating book detail");

        BookEntity book = bookRepository.findByGuid(guid)
                .orElseThrow(() -> new DomainValidationException("Book not found"));

        bookDetailsValidator.validateBookDetails(dto);

        BookDetailsEntity newDetail = new BookDetailsEntity();
        newDetail.setGuid(UUID.randomUUID().toString());
        newDetail.setBookType(dto.getBookType());
        newDetail.setPrice(dto.getPrice());
        newDetail.setStockQuantity(dto.getStockQuantity());
        newDetail.setOnSale(dto.isOnSale());
        newDetail.setFeatured(dto.isFeatured());

        newDetail.setBook(book);

        log.debug("Create book detail [GUDI = {}]", newDetail.getGuid());

        BookDetailsEntity saved = bookDetailsRepository.save(newDetail);
        return bookMapper.toDetailResponse(saved);
    }

    @Transactional
    public BookDetailsResponseDTO updateDetailsBook(@Valid BookDetailsRequestDTO dto, String guid) {

        log.info("Updating book detail [GUID = {}]", guid);

        BookDetailsEntity existingDetailsBook = bookDetailsRepository.findByGuid(guid)
                .orElseThrow(() -> new DomainValidationException("Details not found: " + guid));

        existingDetailsBook.setBookType(dto.getBookType());
        existingDetailsBook.setPrice(dto.getPrice());
        existingDetailsBook.setStockQuantity(dto.getStockQuantity());
        existingDetailsBook.setOnSale(dto.isOnSale());
        existingDetailsBook.setFeatured(dto.isFeatured());

        BookDetailsEntity savedDetailBook = bookDetailsRepository.save(existingDetailsBook);

        log.debug("Updating book [ GUID = {}}]", guid);

        return bookMapper.toDetailResponse(savedDetailBook);
    }

    @Transactional
    public void deleteDetailBook(String guid) {

        log.info("Deleting book GUID: {}", guid);

        BookDetailsEntity existing = bookDetailsRepository.findByGuid(guid)
                .orElseThrow(() -> new DomainValidationException("BookDetail GUID not found: " + guid));

        log.debug("Deleting book detail [GUID = {}]", guid);

        bookDetailsRepository.delete(existing);
    }
}
