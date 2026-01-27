package com.Bookstore.bookstore_api.repository;

import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import com.Bookstore.bookstore_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsEntity, Long> {

    List<BookDetailsEntity> findByOnSale(Boolean onSale);

    List<BookDetailsEntity> findByFeatured(Boolean featured);

    Optional<BookDetailsEntity> findByGuid(String guid);
}
