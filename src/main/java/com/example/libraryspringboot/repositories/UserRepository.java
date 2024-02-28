package com.example.libraryspringboot.repositories;

import com.example.libraryspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
