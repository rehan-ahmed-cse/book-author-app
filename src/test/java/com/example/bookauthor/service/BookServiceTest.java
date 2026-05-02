package com.example.bookauthor.service;

import com.example.bookauthor.dto.BookAuthorDTO;
import com.example.bookauthor.entity.Author;
import com.example.bookauthor.entity.Book;
import com.example.bookauthor.exception.ResourceNotFoundException;
import com.example.bookauthor.repository.BookRepository;
import com.example.bookauthor.service.impl.BookServiceImpl;
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
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book testBook;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Test Author", "American", "Test bio");
        testAuthor.setId(1L);

        testBook = new Book("Test Title", "Fiction", 2023, "123-456", "Test desc", testAuthor);
        testBook.setId(1L);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(testBook));

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Title", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testSaveBook_DuplicateIsbn() {
        when(bookRepository.existsByIsbn("123-456")).thenReturn(true);

        assertThrows(DataIntegrityViolationException.class, () -> {
            bookService.saveBook(testBook);
        });
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testSaveBook_NullIsbn() {
        Book bookWithNullIsbn = new Book("No ISBN", "Fiction", 2020, null, "No ISBN desc", testAuthor);
        when(bookRepository.save(any(Book.class))).thenReturn(bookWithNullIsbn);

        Book saved = bookService.saveBook(bookWithNullIsbn);

        assertNotNull(saved);
        verify(bookRepository, never()).existsByIsbn(any());
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book updatedDetails = new Book();
        updatedDetails.setTitle("Updated Title");
        updatedDetails.setGenre("Non-Fiction");
        updatedDetails.setPublicationYear(2024);
        updatedDetails.setAuthor(testAuthor);

        Book updated = bookService.updateBook(1L, updatedDetails);

        assertNotNull(updated);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testUpdateBook_NotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        Book updatedDetails = new Book();
        updatedDetails.setTitle("Updated");

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBook(99L, updatedDetails);
        });
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        doNothing().when(bookRepository).delete(testBook);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).delete(testBook);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(99L);
        });
    }

    @Test
    void testGetAllBooksWithAuthors() {
        BookAuthorDTO dto = new BookAuthorDTO(1L, "Test Title", "Fiction", 2023, "Test Author", "American");
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(Arrays.asList(dto));

        List<BookAuthorDTO> result = bookService.getAllBooksWithAuthors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Author", result.get(0).getAuthorName());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }
}
