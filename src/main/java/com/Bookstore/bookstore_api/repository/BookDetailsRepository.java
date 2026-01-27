package com.Bookstore.bookstore_api.repository;

import com.Bookstore.bookstore_api.entity.BookDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsEntity, Long> {

    Optional<BookDetailsEntity> findByGuid(String guid);
}
