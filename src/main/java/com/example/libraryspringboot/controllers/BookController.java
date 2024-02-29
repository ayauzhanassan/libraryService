package com.example.libraryspringboot.controllers;

import com.example.libraryspringboot.entities.Book;
import com.example.libraryspringboot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id); // Ensure the ID is set
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public Book borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.borrowBook(bookId, userId);
    }

    @PostMapping("/{bookId}/return")
    public Book returnBook(@PathVariable Long bookId) {
        return bookService.returnBook(bookId);
    }
}
