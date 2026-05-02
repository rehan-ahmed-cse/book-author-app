package com.example.bookauthor.service.impl;

import com.example.bookauthor.entity.Author;
import com.example.bookauthor.exception.ResourceNotFoundException;
import com.example.bookauthor.repository.AuthorRepository;
import com.example.bookauthor.service.AuthorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Override
    public Author saveAuthor(Author author) {
        if (authorRepository.existsByName(author.getName())) {
            throw new DataIntegrityViolationException("Author with name '" + author.getName() + "' already exists", null);
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        if (!author.getName().equals(authorDetails.getName())
                && authorRepository.existsByName(authorDetails.getName())) {
            throw new DataIntegrityViolationException("Author with name '" + authorDetails.getName() + "' already exists", null);
        }
        author.setName(authorDetails.getName());
        author.setNationality(authorDetails.getNationality());
        author.setBiography(authorDetails.getBiography());
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
