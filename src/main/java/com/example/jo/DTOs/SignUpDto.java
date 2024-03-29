package com.example.jo.DTOs;

import com.example.jo.entities.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role) {
}
