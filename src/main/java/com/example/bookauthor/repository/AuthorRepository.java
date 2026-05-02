package com.example.bookauthor.repository;

import com.example.bookauthor.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Author entity.
 * Extends JpaRepository which provides built-in CRUD methods:
 *   - save(), findById(), findAll(), deleteById(), etc.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Custom query method: Spring Data JPA automatically implements this.
     * Checks if an author with the given name already exists.
     */
    boolean existsByName(String name);
}
