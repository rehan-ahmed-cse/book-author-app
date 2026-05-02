package com.example.bookauthor.service;

import com.example.bookauthor.dto.BookAuthorDTO;
import com.example.bookauthor.entity.Book;
import java.util.List;

/**
 * Service interface for Book operations.
 * Includes the custom query method for the INNER JOIN.
 */
public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<BookAuthorDTO> getAllBooksWithAuthors();
}
