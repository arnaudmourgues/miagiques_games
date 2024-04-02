package com.example.jo.entities.DTOs;

import java.util.UUID;

public record SignUpParcipantDto(
        String username,
        String password,
        UUID delegation
) {
}
