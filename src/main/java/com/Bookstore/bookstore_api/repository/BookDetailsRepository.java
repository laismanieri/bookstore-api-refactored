package com.Bookstore.bookstore_api.repository;

import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsEntity, Long> {

    List<BookDetailsEntity> findByOnSale(Boolean onSale);

    List<BookDetailsEntity> findByFeatured(Boolean featured);
}
