package com.example.schoolapi.repository;

import com.example.schoolapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
    Optional<User> findByPassword(String password);

}
