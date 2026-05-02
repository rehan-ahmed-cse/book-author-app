package com.example.bookauthor.service;

import com.example.bookauthor.entity.Author;
import com.example.bookauthor.exception.ResourceNotFoundException;
import com.example.bookauthor.repository.AuthorRepository;
import com.example.bookauthor.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Test Author", "British", "Test biography");
        testAuthor.setId(1L);
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(testAuthor));

        List<Author> authors = authorService.getAllAuthors();

        assertNotNull(authors);
        assertEquals(1, authors.size());
        assertEquals("Test Author", authors.get(0).getName());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorById_Success() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));

        Author found = authorService.getAuthorById(1L);

        assertNotNull(found);
        assertEquals("Test Author", found.getName());
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.getAuthorById(99L);
        });
    }

    @Test
    void testSaveAuthor() {
        when(authorRepository.existsByName("Test Author")).thenReturn(false);
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        Author saved = authorService.saveAuthor(testAuthor);

        assertNotNull(saved);
        assertEquals("Test Author", saved.getName());
        verify(authorRepository, times(1)).existsByName("Test Author");
        verify(authorRepository, times(1)).save(testAuthor);
    }

    @Test
    void testSaveAuthor_DuplicateName() {
        when(authorRepository.existsByName("Test Author")).thenReturn(true);

        assertThrows(DataIntegrityViolationException.class, () -> {
            authorService.saveAuthor(testAuthor);
        });
        verify(authorRepository, never()).save(any());
    }

    @Test
    void testUpdateAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        Author updatedDetails = new Author();
        updatedDetails.setName("Updated Name");
        updatedDetails.setNationality("American");

        Author updated = authorService.updateAuthor(1L, updatedDetails);

        assertNotNull(updated);
        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testUpdateAuthor_NotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        Author updatedDetails = new Author();
        updatedDetails.setName("Updated Name");

        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.updateAuthor(99L, updatedDetails);
        });
    }

    @Test
    void testUpdateAuthor_DuplicateName() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.existsByName("Other Author")).thenReturn(true);

        Author updatedDetails = new Author();
        updatedDetails.setName("Other Author");

        assertThrows(DataIntegrityViolationException.class, () -> {
            authorService.updateAuthor(1L, updatedDetails);
        });
        verify(authorRepository, never()).save(any());
    }

    @Test
    void testUpdateAuthor_SameNameAllowed() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        Author updatedDetails = new Author();
        updatedDetails.setName("Test Author");
        updatedDetails.setNationality("French");

        Author updated = authorService.updateAuthor(1L, updatedDetails);

        assertNotNull(updated);
        verify(authorRepository, never()).existsByName(any());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testDeleteAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        doNothing().when(authorRepository).delete(testAuthor);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).delete(testAuthor);
    }

    @Test
    void testDeleteAuthor_NotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.deleteAuthor(99L);
        });
    }
}
