package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LibraryController {
    private final LibraryService bookService;

    public LibraryController(LibraryService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "view_books";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id).orElseThrow());
        return "update_book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        System.out.println(book.getStatus());
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String title, Model model) {
        model.addAttribute("books", bookService.searchBookByTitle(title));
        return "search_book";
    }
    @GetMapping("/searchId")
    public String searchBookById(@RequestParam("bookId") Long bookId, Model model) {
        Book book = bookService.searchBookId(bookId);
        if (book != null) {
            model.addAttribute("book", book);
            return "book_detail"; // Display book details
        } else {
            model.addAttribute("error", "Book not found");
            return "view_books"; // Redirect to book list with an error message
        }
    }


}
