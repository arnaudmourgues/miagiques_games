package com.example.jo.entities.DTOs;

public record SignUpUserDto(
        String email,
        String nom,
        String prenom,
        String password) {
}
