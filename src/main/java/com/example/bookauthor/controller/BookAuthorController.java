package com.example.bookauthor.controller;

import com.example.bookauthor.dto.BookAuthorDTO;
import com.example.bookauthor.entity.Author;
import com.example.bookauthor.entity.Book;
import com.example.bookauthor.service.AuthorService;
import com.example.bookauthor.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookAuthorController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookAuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("bookCount", bookService.getAllBooks().size());
        model.addAttribute("authorCount", authorService.getAllAuthors().size());
        return "index";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/books-with-authors")
    public String booksWithAuthors(Model model) {
        List<BookAuthorDTO> bookAuthorList = bookService.getAllBooksWithAuthors();
        model.addAttribute("bookAuthorList", bookAuthorList);
        return "booksWithAuthors";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "addBook";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "addBook";
        }
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
        return "redirect:/books";
    }

    @GetMapping("/authors/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@Valid @ModelAttribute("author") Author author,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addAuthor";
        }
        authorService.saveAuthor(author);
        redirectAttributes.addFlashAttribute("successMessage", "Author added successfully!");
        return "redirect:/authors";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "editBook";
    }

    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "editBook";
        }
        bookService.updateBook(id, book);
        redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
        return "redirect:/books";
    }

    @GetMapping("/authors/edit/{id}")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/authors/edit/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") Author author,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "editAuthor";
        }
        authorService.updateAuthor(id, author);
        redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully!");
        return "redirect:/authors";
    }
}
