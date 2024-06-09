package com.example.jo.entities.DTOs;

public record SignUpDto(
        String email,
        String password,
        String role) {
}
