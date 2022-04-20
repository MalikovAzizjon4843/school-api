package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);
//    List<Book> findAllByAClass_Value(int AClass_value);
    List<Book> findAllByLanguage(Language language);
}
