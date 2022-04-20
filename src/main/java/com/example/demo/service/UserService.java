package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.enums.Classes;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;

    public ApiResponse add(UserDTO userDTO) {
        int sinfi = userDTO.getSinfi();
        String fullName = userDTO.getFullName();
        Classes[] classes = Classes.values();
        Classes c = classes[sinfi];
        User user = new User();
        user.setAClass(c);
        user.setUsername(fullName);
        userRepository.save(user);
        return new ApiResponse("User added", 201);
    }

    public List<Book> getFollowingBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getBookList();
    }
}
