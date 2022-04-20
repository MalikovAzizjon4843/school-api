package com.example.schoolapi.controller;

import com.example.schoolapi.dto.ApiResponse;
import com.example.schoolapi.entity.User;
import com.example.schoolapi.dto.UserDTO;
import com.example.schoolapi.repository.UserRepository;
import com.example.schoolapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    final UserRepository userRepository;
    final UserService userService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getone(@PathVariable Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        return ResponseEntity.status(userOptional.isEmpty() ? 404 : 200)
                .body(userOptional.isEmpty() ? "User topilmadi" : userOptional.get());
    }
    @PostMapping()
    public ResponseEntity add(@RequestBody UserDTO userDTO){
          ApiResponse response = userService.add(userDTO);
          return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }
    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            return  ResponseEntity.status(404).body("User topilmadi!!!");
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User deleted!!!");
    }
//    @GetMapping("/getFollowingBooks")
//    public ResponseEntity getFollowingBooks (){
//        List<Book> bookList = userService.getFollowingBooks();
//        return ResponseEntity.ok().body(bookList);
//    }
}
