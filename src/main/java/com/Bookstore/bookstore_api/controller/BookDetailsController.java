package com.Bookstore.bookstore_api.controller;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookRepository;
import com.Bookstore.bookstore_api.service.BookDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/api/books-details")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<BookDetailsResponseDTO>> getAll() {
        return ResponseEntity.ok(bookDetailsService.listAllBookDetail());
    }

    @GetMapping("/{guid}")
    public ResponseEntity<BookDetailsResponseDTO> getByGuid(@PathVariable String guid) {
        return ResponseEntity.ok(bookDetailsService.getBookDetailByGuid(guid));
    }

    @PostMapping("/{bookGuid}/details")
    public ResponseEntity<BookDetailsResponseDTO> createBookDetail(
            @PathVariable String bookGuid,
            @Valid @RequestBody BookDetailsRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookDetailsService.createBookDetail(dto, bookGuid));
    }

    @PutMapping("/{guid}")
    public ResponseEntity<BookDetailsResponseDTO> updateBookDetails(
            @PathVariable String guid,
            @Valid @RequestBody BookDetailsRequestDTO bookDetailsRequestDTO) {
        BookDetailsResponseDTO updateBookDetails = bookDetailsService.updateDetailsBook(bookDetailsRequestDTO, guid);
        return ResponseEntity.ok(updateBookDetails);
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteDetailBook (@PathVariable String guid){
        bookDetailsService.deleteDetailBook(guid);
        return ResponseEntity.noContent().build();
    }
}
