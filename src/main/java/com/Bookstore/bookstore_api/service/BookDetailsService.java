package com.Bookstore.bookstore_api.service;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookDetailsRepository;
import com.Bookstore.bookstore_api.validator.BookDetailsValidator;
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

//    @Transactional
//    public BookDetailsEntity updateDetailsBook(BookDetailsRequestDTO dto) {
//
//        bookDetailsValidator.validateBookDetails(dto);
//
//        if (dto.getDetails() != null) {
//            List<BookDetailsEntity> detailsEntities = dto.getDetails().stream()
//                    .map(detailDto -> {
//
//        BookDetailsEntity newBookDetails = new BookDetailsEntity();
//        newBookDetails.setGuid(UUID.randomUUID().toString());
//        newBookDetails.setBookType(dto.getBookType());
//        newBookDetails.setPrice(dto.getPrice());
//        newBookDetails.setStockQuantity(dto.getStockQuantity());
//        newBookDetails.setOnSale(dto.isOnSale());
//        newBookDetails.setFeatured(dto.isFeatured());
//
//
//        return detalhelivroRepository.save(detalheLivroario);
//    }

}
