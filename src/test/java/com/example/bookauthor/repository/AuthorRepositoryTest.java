package com.example.bookauthor.repository;

import com.example.bookauthor.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testExistsByName_WhenExists() {
        Author author = new Author("Jane Austen", "British", "Romantic fiction novelist");
        entityManager.persist(author);
        entityManager.flush();

        assertTrue(authorRepository.existsByName("Jane Austen"));
    }

    @Test
    void testExistsByName_WhenNotExists() {
        assertFalse(authorRepository.existsByName("Nonexistent Author"));
    }

    @Test
    void testFindAll() {
        Author author1 = new Author("Author One", "American", "Bio one");
        Author author2 = new Author("Author Two", "British", "Bio two");
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.flush();

        var authors = authorRepository.findAll();
        assertEquals(2, authors.size());
    }

    @Test
    void testFindById() {
        Author author = new Author("Test Author", "American", "Test bio");
        entityManager.persist(author);
        entityManager.flush();

        var found = authorRepository.findById(author.getId());
        assertTrue(found.isPresent());
        assertEquals("Test Author", found.get().getName());
    }
}
