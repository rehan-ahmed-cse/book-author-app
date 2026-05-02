package com.example.bookauthor.repository;

import com.example.bookauthor.dto.BookAuthorDTO;
import com.example.bookauthor.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Book entity.
 * Extends JpaRepository for built-in CRUD operations.
 * Contains a custom JPQL query for INNER JOIN between Book and Author.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * CUSTOM QUERY: INNER JOIN between Book and Author entities.
     * 
     * This JPQL query:
     * 1. Creates a new BookAuthorDTO object for each result row
     * 2. Performs an INNER JOIN between Book (b) and its Author (a)
     * 3. Returns a list of DTOs containing combined data
     * 
     * The 'new' keyword in JPQL requires a fully qualified class name
     * and a constructor matching the selected fields.
     */
    @Query("SELECT new com.example.bookauthor.dto.BookAuthorDTO(" +
           "b.id, b.title, b.genre, b.publicationYear, a.name, a.nationality) " +
           "FROM Book b INNER JOIN b.author a")
    List<BookAuthorDTO> findAllBooksWithAuthors();

    /**
     * Custom query method to check for duplicate ISBN.
     */
    boolean existsByIsbn(String isbn);
}
