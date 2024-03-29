package com.example.jo.entities.DTOs;

import com.example.jo.entities.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role) {
}
