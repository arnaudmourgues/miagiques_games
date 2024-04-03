package com.example.jo.entities.DTOs;

import java.util.UUID;

public record UpdateEpreuveDto(
        UUID id,
        EpreuveDto epreuve
) {
}
