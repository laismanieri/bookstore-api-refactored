package com.Bookstore.bookstore_api.controller;

import com.Bookstore.bookstore_api.dto.BookDetailsRequestDTO;
import com.Bookstore.bookstore_api.dto.BookDetailsResponseDTO;
import com.Bookstore.bookstore_api.mapper.BookMapper;
import com.Bookstore.bookstore_api.repository.BookRepository;
import com.Bookstore.bookstore_api.service.BookDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<BookDetailsResponseDTO>> getAllBookDetail() {
        return ResponseEntity.ok(bookDetailsService.listAllBookDetail());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<BookDetailsResponseDTO> getBookDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(bookDetailsService.getBookDetailById(id));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<BookDetailsResponseDTO> updateBookDetails(
            @PathVariable Long id,
            @Valid @RequestBody BookDetailsRequestDTO bookDetailsRequestDTO) {
        BookDetailsResponseDTO updateBookDetails = bookDetailsService.updateDetailsBook(bookDetailsRequestDTO, id);
        return ResponseEntity.ok(updateBookDetails);
    }

    @DeleteMapping(value = "{guid}")
    public ResponseEntity<Void> deleteDetailBook (@PathVariable String guid){
        bookDetailsService.deleteDetailBook(guid);
        return ResponseEntity.noContent().build();
    }


}
