package com.example.libraryspringboot.controllers;


import com.example.libraryspringboot.entities.Book;
import com.example.libraryspringboot.entities.Loan;
import com.example.libraryspringboot.repositories.BookRepository;
import com.example.libraryspringboot.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createLoan(@RequestBody Loan loan ) {
        Book book = bookRepository.findById(loan.getBook().getId()).orElse(null);
        if (book != null) {
            try {
                loanService.createLoan(book, loan.getUser().getId());
                return new ResponseEntity<>("Loan created successfully", HttpStatus.CREATED);
            } catch (RuntimeException e) {
                // Handle the exception
                return new ResponseEntity<>("Failed to create loan: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // Handle book not found scenario
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/complete")
    public void completeLoan(@PathVariable Long booikId) {
        loanService.completeLoan(booikId);
    }

    @GetMapping("/user/{userid}")
    public List<Book> getBooksBorrowedByUser(@PathVariable Long userId) {
        return loanService.findBooksBorrowedByUser(userId);
    }
}
