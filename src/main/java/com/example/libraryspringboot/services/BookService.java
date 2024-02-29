package com.example.libraryspringboot.services;

import com.example.libraryspringboot.entities.Book;
import com.example.libraryspringboot.entities.Loan;
import com.example.libraryspringboot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanService loanService;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            book = save(book);

            loanService.createLoan(book, userId);
            return book;
        } else {
            return null;
        }
    }

    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            book = save(book);

            // Update loan status
            loanService.completeLoan(bookId);
            return book;
        } else {
            return null;
        }
    }
}
