package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface RoleRepository {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role getRoleByName(String name);
}

