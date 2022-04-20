package com.example.demo.entity.enums;

public enum RoleEnum {
    //static
    ROLE_USER("user"), //tengkuchli == RolEnum enum = new RoleEnum("Role USER")
    ROLE_ADMIN("admin");
    private String description;

    RoleEnum(String description) {
        this.description = description;
    }
}
