package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookDetailsRepository;
import com.Bookstore.bookstore_api.validator.BookDetailsValidator;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
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
    public BookDetailsResponseDTO getBookDetailById(Long id) {

        log.info("Finding detail by ID: {}", id);

        BookDetailsEntity bookDetail = bookDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found: " + id));
        return bookMapper.toDetailResponse(bookDetail);
    }

    @Transactional
    public BookDetailsResponseDTO createBookDetail(BookDetailsRequestDTO dto) {

        log.info("Creating book detail");

        bookDetailsValidator.validateBookDetails(dto);

        BookDetailsEntity newDetail = new BookDetailsEntity();
        newDetail.setGuid(UUID.randomUUID().toString());
        newDetail.setBookType(dto.getBookType());
        newDetail.setPrice(dto.getPrice());
        newDetail.setStockQuantity(dto.getStockQuantity());
        newDetail.setOnSale(dto.isOnSale());
        newDetail.setFeatured(dto.isFeatured());

        log.debug("Create book detail [ID = {}]", newDetail.getId());

        BookDetailsEntity saved = bookDetailsRepository.save(newDetail);
        return bookMapper.toDetailResponse(saved);
    }


    @Transactional
    public BookDetailsResponseDTO updateDetailsBook(@Valid BookDetailsRequestDTO dto, Long id) {

        log.info("Updating book detail [ID = {}]", id);

        BookDetailsEntity existingDetailsBook = bookDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found: " + id));

        existingDetailsBook.setBookType(dto.getBookType());
        existingDetailsBook.setPrice(dto.getPrice());
        existingDetailsBook.setStockQuantity(dto.getStockQuantity());
        existingDetailsBook.setOnSale(dto.isOnSale());
        existingDetailsBook.setFeatured(dto.isFeatured());

        BookDetailsEntity savedDetailBook = bookDetailsRepository.save(existingDetailsBook);

        log.debug("Updating book [ id = {}}]", id);

        return bookMapper.toDetailResponse(savedDetailBook);
    }

    @Transactional
    public void deleteDetailBook(String guid) {

        log.info("Deleting book GUID: {}", guid);

        BookDetailsEntity existing = bookDetailsRepository.findByGuid(guid)
                .orElseThrow(() -> new ValidationException("BookDetail GUID not found: " + guid));

        log.debug("Deleting book detail [GUID = {}]", guid);

        bookDetailsRepository.delete(existing);
    }
}
