package com.example.libraryspringboot.repositories;

import com.example.libraryspringboot.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {
//    @Query("SELECT COUNT(1) FROM loan 1 WHERE 1.user.id = :userId")
//    int countBooksBorrowedByUser(@Param("userId") Long userId);
}
