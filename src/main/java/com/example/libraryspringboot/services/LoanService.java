package com.example.libraryspringboot.services;

import com.example.libraryspringboot.entities.Book;
import com.example.libraryspringboot.entities.Loan;
import com.example.libraryspringboot.entities.User;
import com.example.libraryspringboot.repositories.BookRepository;
import com.example.libraryspringboot.repositories.LoanRepository;
import com.example.libraryspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public void deleteById(Long id) {
        loanRepository.deleteById(id);
    }


    public void createLoan(Book book, Long userId) {
        int numBooksBorrowed = countBooksBorrowedByUser(userId);
        if (numBooksBorrowed >= 5) {
            throw new RuntimeException("Maximum number of books borrowed reached for the user.");
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.getUser().setId(userId);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(14)); // Return date 14 days after loan date
        loanRepository.save(loan);

        book.setAvailable(false);
        bookRepository.save(book);
    }

    public int countBooksBorrowedByUser(Long userId) {
        return loanRepository.countBooksBorrowedByUser(userId);
    }

    public void completeLoan(Long bookId) {
        List<Loan> loans = loanRepository.findAll();
        for (Loan loan : loans) {
            if (loan.getBook().getId().equals(bookId)) {
                Book book = loan.getBook();
                book.setAvailable(true);
                bookRepository.save(book);

                loanRepository.delete(loan);
                break;
            }
        }
    }

    public List<Book> findBooksBorrowedByUser(Long userId) {
        List<Book> booksBorrowedByUser = new ArrayList<>();
        List<Loan> loans = loanRepository.findAll();
        for (Loan loan : loans) {
            if (loan.getUser().getId().equals(userId)) {
                booksBorrowedByUser.add((loan.getBook()));
            }
        }
        return booksBorrowedByUser;
    }
}
