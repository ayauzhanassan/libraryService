package com.example.libraryspringboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate loanDate;

    private LocalDate returnDate;

    public void setReturnDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        this.returnDate = loanDate.plusDays(14);
    }
}
