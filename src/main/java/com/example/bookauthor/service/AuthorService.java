package com.example.bookauthor.service;

import com.example.bookauthor.entity.Author;
import java.util.List;

/**
 * Service interface for Author operations.
 * Defines the contract that the implementation must fulfill.
 * Using interfaces allows for easy mocking in unit tests.
 */
public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}
