package com.Bookstore.bookstore_api.mapper;

import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.dto.BookResponseDTO;
import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponseDTO toResponse(BookEntity book) {
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

        if (book.getDetails() != null) {
            dto.setDetails(
                    book.getDetails().stream()
                            .map(this::toDetailResponse)
                            .toList()
            );
        }

        return dto;
    }

    private BookDetailsResponseDTO toDetailResponse(BookDetailsEntity detail) {
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
