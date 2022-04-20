package com.example.demo.service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Classes;
import com.example.demo.entity.enums.Language;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    final BookRepository bookRepository;
    final UserRepository userRepository;

    public List<Book> getByClass(int classValue) {
        List<Book> all = bookRepository.findAll();
        for (Book book : all) {
            if (book.getAClass().getValue() != classValue)
                all.remove(book);
        }
        return all;
//        return bookRepository.findAllByAClass_Value(classValue);
//        return null;
    }

    public List<Book> getByLanguage(int langId) {
        Language[] languages = Language.values();
        Language language = languages[langId];
        return bookRepository.findAllByLanguage(language);
//        return null;
    }

    public ApiResponse addFollow(Integer bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Book> bookList = user.getBookList();
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty())
            return new ApiResponse("Book not found!!!", 404);
        bookList.add(bookOptional.get());
        user.setBookList(bookList);
        userRepository.save(user);
        return new ApiResponse("ZO'R", 201);
    }

    public ApiResponse saveBook(BookDTO bookDTO) {
        String data = bookDTO.getData();
        int forClass = bookDTO.getForClass();
        String description = bookDTO.getDescription();
        int language = bookDTO.getLanguage();
        String name = bookDTO.getName();
        Classes classes;
        Language lang;
        if (name==null || description==null || data == null || language == 0 || forClass == 0)
            return new ApiResponse("Parametrlar noto'g'ri!!!", 400);
        try {
            Classes[] classes1 = Classes.values();
            Language[] languages = Language.values();
            classes = classes1[forClass];
            lang = languages[language];
        }catch (Exception e){
            return new ApiResponse(e.getMessage(), 400);
        }
        bookRepository.save(new Book(name, description, lang, data, classes));
        return new ApiResponse("Book added!!!", 201);
    }
}






