package com.example.libraryspringboot.repositories;

import com.example.libraryspringboot.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
