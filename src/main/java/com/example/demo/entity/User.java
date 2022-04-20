package com.example.demo.entity;

import com.example.demo.entity.enums.Classes;
import com.example.demo.entity.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @Enumerated(EnumType.STRING)
    private Classes aClass;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> bookList;
    private String password;
//    @CreatedDate
    private LocalDate date = LocalDate.now();
//    @Enumerated(EnumType.STRING)
//    private RoleEnum roleName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roleSet;

    public User(String username, Classes aClass, String password) {
        this.username = username;
        this.aClass = aClass;
        this.password = password;
    }

    private boolean active = true;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        for (Role role : this.roleSet) {
            for (PermissionEnum permissionEnum : role.getPermissionEnum()) {
                authorities.add(new SimpleGrantedAuthority(permissionEnum.name()));
            }
        }
//
//        //preauthorize hasRole
////        for (Role role : this.roleList) {
////            authorities.add(role.getRoleName());
////        }
        return authorities;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
//        authorities.add(new SimpleGrantedAuthority( this.roleName.name()));
//        return authorities;
//    }
}
