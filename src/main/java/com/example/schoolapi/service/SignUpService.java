package com.example.schoolapi.service;

import com.example.schoolapi.dto.ApiResponse;
import com.example.schoolapi.dto.Sign;
import com.example.schoolapi.entity.Role;
import com.example.schoolapi.entity.User;
import com.example.schoolapi.entity.enums.Classes;
import com.example.schoolapi.repository.RoleRepository;
import com.example.schoolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public ApiResponse signUp(Sign sign) {
        String userName = sign.getUserName();
        String password = sign.getPassword();
        Optional<User> byUsername = userRepository.findByUsername(userName);
        Optional<User> byPassword = userRepository.findByPassword(password);
        if (byUsername.isPresent() && byPassword.isPresent())
            if (byPassword.get() == byUsername.get())
                return new ApiResponse("Bu login yoki parol allaqachon mavjud iltimos boshqa kirting.", 400, false);
//        String aClass = String.valueOf(sign.getAClass());
        Classes [] classes1 = Classes.values();
        Classes classes = classes1[sign.getAClass()];
        Role role = roleRepository.findById(1).get();
//        try {
//        classes = Classes.valueOf(sign.getAClass());
//        }catch (Exception e){

//        }
        User user = new User();
        user.setUsername(sign.getUserName());
        user.setPassword(passwordEncoder.encode(sign.getPassword()));
        user.setAClass(classes);
        User save = userRepository.save(user);
        return new ApiResponse("Added!!!", 201, true);
    }
}
