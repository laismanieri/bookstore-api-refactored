package com.Bookstore.bookstore_api.repository;

import com.Bookstore.bookstore_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByGenre(String genre);

    List<BookEntity> findByPublisher(String publisher);

    List<BookEntity> findByPublicationYear(int publicationYear);

    List<BookEntity> findByAuthor(String author);

    List<BookEntity> findByPageCount(int pageCount);
}

