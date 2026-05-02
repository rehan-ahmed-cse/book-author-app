package com.example.bookauthor.repository;

import com.example.bookauthor.dto.BookAuthorDTO;
import com.example.bookauthor.entity.Author;
import com.example.bookauthor.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindAllBooksWithAuthors() {
        Author author = new Author("John Doe", "American", "Bio");
        entityManager.persist(author);

        Book book = new Book("Test Book", "Fiction", 2023, "123", "Desc", author);
        entityManager.persist(book);

        entityManager.flush();

        List<BookAuthorDTO> result = bookRepository.findAllBooksWithAuthors();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Book", result.get(0).getBookTitle());
        assertEquals("John Doe", result.get(0).getAuthorName());
    }

    @Test
    void testExistsByIsbn() {
        Author author = new Author("Jane Doe", "British", "Bio");
        entityManager.persist(author);

        Book book = new Book("Another Book", "Non-Fiction", 2022, "999", "Desc", author);
        entityManager.persist(book);
        entityManager.flush();

        assertTrue(bookRepository.existsByIsbn("999"));
        assertFalse(bookRepository.existsByIsbn("000"));
    }

    @Test
    void testFindById() {
        Author author = new Author("Test Author", "American", "Bio");
        entityManager.persist(author);

        Book book = new Book("Find Me", "Fiction", 2020, "456", "Desc", author);
        entityManager.persist(book);
        entityManager.flush();

        var found = bookRepository.findById(book.getId());
        assertTrue(found.isPresent());
        assertEquals("Find Me", found.get().getTitle());
    }

    @Test
    void testFindById_NotFound() {
        var found = bookRepository.findById(9999L);
        assertFalse(found.isPresent());
    }

    @Test
    void testSaveBook() {
        Author author = new Author("Save Author", "American", "Bio");
        entityManager.persist(author);
        entityManager.flush();

        Book book = new Book("Save Test", "Sci-Fi", 2024, "789", "Save desc", author);
        Book saved = bookRepository.save(book);

        assertNotNull(saved.getId());
    }

    @Test
    void testDeleteBook() {
        Author author = new Author("Delete Author", "American", "Bio");
        entityManager.persist(author);

        Book book = new Book("Delete Me", "Fiction", 2021, "321", "Delete desc", author);
        entityManager.persist(book);
        entityManager.flush();

        Long bookId = book.getId();
        bookRepository.deleteById(bookId);

        var found = bookRepository.findById(bookId);
        assertFalse(found.isPresent());
    }
}
