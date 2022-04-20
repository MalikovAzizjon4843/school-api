package com.example.demo.entity;

import com.example.demo.entity.enums.Classes;
import com.example.demo.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
//    private String photo;
    @Enumerated(EnumType.STRING)
    private Language language;
    private String data;
    @Enumerated(EnumType.STRING)
    private Classes aClass;

    public Book(String name, String description, Language language, String data, Classes aClass) {
        this.name = name;
        this.description = description;
//        this.photo = photo;
        this.language = language;
        this.data = data;
        this.aClass = aClass;
    }

    public Book(String name, String description, String data) {
        this.name = name;
        this.description = description;
//        this.photo = photo;
        this.data = data;
    }

}
