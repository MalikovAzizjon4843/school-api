package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    final BookRepository bookRepository;
    final BookService bookService;
    final UserService userService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(bookRepository.findAll());
    }

    @GetMapping("/getByName/{bookName}")
    public ResponseEntity getByName(@RequestParam String bookName){
        Optional<Book> byName = bookRepository.findByName(bookName);
        return ResponseEntity.status(byName.isEmpty() ? 404 : 200).
                body(byName.isEmpty() ? "Book not found." : byName.get());
    }

    @GetMapping("/getAllByClass/{classValue}")
    public ResponseEntity getAllByClass(@PathVariable int classValue){
        List<Book> bookList = bookService.getByClass(classValue);
        return ResponseEntity.ok().body(bookList);
    }
    @GetMapping("/getAllByLanguage/{langId}")
    public ResponseEntity getAllByLanguage(@PathVariable int langId){
        return ResponseEntity.ok().body(bookService.getByLanguage(langId));
    }
    @GetMapping("/addFollow/{bookId}")
    public ResponseEntity addFollow(@PathVariable Integer bookId){
        ApiResponse response = bookService.addFollow(bookId);
        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }
//    @PreAuthorize("hasAuthority('READ_BOOK')")
    @GetMapping("/getFollowingBooks")
    public ResponseEntity getFollowingBooks (){
        List<Book> bookList = userService.getFollowingBooks();
        return ResponseEntity.ok().body(bookList);
    }

    @PreAuthorize("hasAuthority(ADD_BOOK)")
    @PostMapping
    public ResponseEntity add(@RequestBody BookDTO bookDTO){
        ApiResponse response = bookService.saveBook(bookDTO);
        return ResponseEntity.ok().body(response.getMessage());
    }
}
