package com.Bookstore.bookstore_api.mapper;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.dto.BookRequestDTO;
import com.Bookstore.bookstore_api.dto.BookResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookEntity toEntity(BookRequestDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setPublisher(dto.getPublisher());
        entity.setPublicationYear(dto.getPublicationYear());
        entity.setPageCount(dto.getPageCount());
        entity.setSynopsis(dto.getSynopsis());
        entity.setGenre(dto.getGenre());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }

    public BookResponseDTO toResponse(BookEntity book) {
        if (book == null) return null;

        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setPageCount(book.getPageCount());
        dto.setSynopsis(book.getSynopsis());
        dto.setGenre(book.getGenre());
        dto.setImageUrl(book.getImageUrl());

        if (book.getDetails() != null && !book.getDetails().isEmpty()) {
            dto.setDetails(
                    book.getDetails().stream()
                            .map(this::toDetailResponse)
                            .toList()
            );
        }

        return dto;
    }

    public BookDetailsResponseDTO toDetailResponse(BookDetailsEntity detail) {
        BookDetailsResponseDTO dto = new BookDetailsResponseDTO();
        dto.setBookType(detail.getBookType());
        dto.setPrice(detail.getPrice());
        dto.setStockQuantity(detail.getStockQuantity());
        dto.setOnSale(detail.isOnSale());
        dto.setFeatured(detail.isFeatured());
        dto.setBookId(detail.getBook().getId());

        float discountedPrice = detail.isOnSale()
                ? detail.getPrice() * 0.9f
                : detail.getPrice();

        dto.setDiscountedPrice(discountedPrice);

        return dto;
    }
}
