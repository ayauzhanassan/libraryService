package com.example.libraryspringboot.repositories;

import com.example.libraryspringboot.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
