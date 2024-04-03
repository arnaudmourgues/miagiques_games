package com.example.jo.entities.DTOs;

import java.util.UUID;

public record SignUpParcipantDto(
        String login,
        String password,
        UUID delegationId
) {
}
