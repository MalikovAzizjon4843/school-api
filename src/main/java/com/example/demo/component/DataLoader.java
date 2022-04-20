package com.example.demo.component;

import com.example.demo.entity.Book;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Classes;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.PermissionEnum;
import com.example.demo.entity.enums.RoleEnum;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;


    final UserRepository userRepository;
    final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("Aziz");
        user.setPassword(passwordEncoder.encode("4843"));
        Role student = new Role( RoleEnum.ROLE_USER);
        List <PermissionEnum> permissionEnums = new ArrayList<>();
        permissionEnums.add(PermissionEnum.READ_BOOK);
        student.setPermissionEnum(permissionEnums);
        permissionEnums.clear();
        PermissionEnum[] enums = PermissionEnum.values();
        for (int i = 0; i < enums.length-1; i++) {
            permissionEnums.add(enums[i]);
        }
//        permissionEnums.add(PermissionEnum.ADD_BOOK);
//        permissionEnums.add(PermissionEnum.DELETE_BOOK);
        Role admin = new Role( RoleEnum.ROLE_ADMIN);
        admin.setPermissionEnum(permissionEnums);
        Set<Role> roleSet = new LinkedHashSet<>();
        roleSet.add(student);
        roleSet.add(admin);
        user.setRoleSet(roleSet);
        userRepository.save(user);
        Book book = new Book("Java", "Nomaum", Language.UZBEK, "akygf8ore4wfckjsdnb viylEUW", Classes.BESH);
        bookRepository.save(book);

//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("user"));
//        user.setRoleEnum(RoleEnum.USER);
//        userRepository.save(user);
//
//        User admin = new User();
//        admin.setRoleEnum(RoleEnum.ADMIN);
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        userRepository.save(admin);



    }
}
