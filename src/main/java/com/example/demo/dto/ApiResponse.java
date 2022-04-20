package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private int status;
    private boolean success;

    public ApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
