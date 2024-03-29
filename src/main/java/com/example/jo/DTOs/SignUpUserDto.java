package com.example.jo.DTOs;

import com.example.jo.entities.enums.UserRole;

public record SignUpUserDto(
        String login,
        String password) {
}
