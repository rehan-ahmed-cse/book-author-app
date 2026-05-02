package com.example.bookauthor.service.impl;

import com.example.bookauthor.dto.BookAuthorDTO;
import com.example.bookauthor.entity.Book;
import com.example.bookauthor.exception.ResourceNotFoundException;
import com.example.bookauthor.repository.BookRepository;
import com.example.bookauthor.service.BookService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getIsbn() != null && bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DataIntegrityViolationException("Book with ISBN '" + book.getIsbn() + "' already exists", null);
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        
        if (bookDetails.getIsbn() != null && !bookDetails.getIsbn().equals(book.getIsbn())) {
            if (bookRepository.existsByIsbn(bookDetails.getIsbn())) {
                throw new DataIntegrityViolationException("Book with ISBN '" + bookDetails.getIsbn() + "' already exists", null);
            }
        }
        
        book.setTitle(bookDetails.getTitle());
        book.setGenre(bookDetails.getGenre());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setIsbn(bookDetails.getIsbn());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    @Override
    public List<BookAuthorDTO> getAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }
}
