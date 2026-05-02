package com.example.bookauthor.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private Model model;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        model = mock(Model.class);
    }

    @Test
    void testHandleDataIntegrityViolation() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Duplicate key");

        String viewName = handler.handleDataIntegrityViolation(ex, model);

        assertEquals("error", viewName);
        verify(model).addAttribute(eq("errorMessage"), contains("Duplicate key"));
    }

    @Test
    void testHandleResourceNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Book not found with id: 99");

        String viewName = handler.handleResourceNotFound(ex, model);

        assertEquals("error", viewName);
        verify(model).addAttribute(eq("errorMessage"), eq("Book not found with id: 99"));
    }

    @Test
    void testHandleGeneralException() {
        Exception ex = new RuntimeException("Something went wrong");

        String viewName = handler.handleGeneralException(ex, model);

        assertEquals("error", viewName);
        verify(model).addAttribute(eq("errorMessage"), contains("Something went wrong"));
    }
}
