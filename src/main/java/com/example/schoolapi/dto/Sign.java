package com.example.schoolapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sign {
//    @NotNull
    private String userName, password;
    private Integer aClass;
}
