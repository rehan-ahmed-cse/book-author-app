package com.example.bookauthor.exception;

/**
 * Custom exception thrown when a requested resource (Book or Author) is not found.
 * Extends RuntimeException so it doesn't need to be declared in method signatures.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
