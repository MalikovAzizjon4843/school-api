package com.example.demo.repository;

import com.example.demo.entity.Role;
import com.example.demo.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
