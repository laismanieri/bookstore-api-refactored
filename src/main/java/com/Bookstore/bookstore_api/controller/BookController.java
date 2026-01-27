package com.Bookstore.bookstore_api.controller;

import com.Bookstore.bookstore_api.dto.BookDTO;
import com.Bookstore.bookstore_api.entity.BookEntity;
import com.Bookstore.bookstore_api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookEntity> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookEntity newBook = bookService.addNewBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

}
