package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.dto.BookResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookDetailsRepository;
import com.Bookstore.bookstore_api.validator.BookDetailsValidator;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return bookDetailsRepository.findAll()
                .stream()
                .map(bookMapper::toDetailResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDetailsResponseDTO getBookDetailById(Long id) {
        BookDetailsEntity bookDetail = bookDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDetailResponse(bookDetail);
    }

    @Transactional
    public BookDetailsResponseDTO updateDetailsBook(@Valid BookDetailsRequestDTO dto, Long id) {

        BookDetailsEntity existingDetailsBook = bookDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found"));

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
    public void deleteDetailBook(String guid){
        BookDetailsEntity existingBookDetail = bookDetailsRepository.findByGuid(guid)
                .orElseThrow(() -> new ValidationException("BookDetail not found"));
        log.debug("Deleting book [ GUID = {} ]", guid);
        bookDetailsRepository.delete(existingBookDetail);
    }
}
