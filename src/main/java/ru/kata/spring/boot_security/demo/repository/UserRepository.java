package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends {
    User findByEmail(String email);
}

