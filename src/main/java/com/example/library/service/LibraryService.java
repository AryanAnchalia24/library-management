package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.LibraryRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private  final LibraryRepository bookRepository;

    public LibraryService(LibraryRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public  Optional<Book> getBookById(Long id) {
        return Optional.of(bookRepository.findById(id).orElseThrow());
    }

    public List<Book> searchBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setStatus(bookDetails.getStatus());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    public Book searchBookId(Long id){
        return bookRepository.findById(id).orElse(null);
    }
}
