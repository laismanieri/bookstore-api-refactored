package com.Bookstore.bookstore_api.exceptions;

public class DomainValidationException extends RuntimeException {
    public DomainValidationException(String message) {
        super(message);
    }
}
