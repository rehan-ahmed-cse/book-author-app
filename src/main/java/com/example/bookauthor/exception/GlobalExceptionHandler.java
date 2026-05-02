package com.example.bookauthor.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global Exception Handler - Catches exceptions across all controllers.
 * @ControllerAdvice makes this class apply to all @Controller classes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles database integrity violations (e.g., duplicate ISBN).
     * Redirects to error.jsp with a user-friendly message.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorMessage", "Data integrity violation: " + ex.getMessage());
        return "error";
    }

    /**
     * Handles cases where a Book or Author ID doesn't exist.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * Catch-all handler for any unexpected exceptions.
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return "error";
    }
}
