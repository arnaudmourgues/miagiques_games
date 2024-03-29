package com.example.jo.entities.DTOs;

import com.example.jo.entities.enums.UserRole;

public record SignUpUserDto(
        String login,
        String password) {
}
