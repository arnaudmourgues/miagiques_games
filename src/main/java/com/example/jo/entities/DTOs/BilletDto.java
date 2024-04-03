package com.example.jo.entities.DTOs;

import java.util.UUID;

public record BilletDto(
        UUID epreuveId,
        double prix,
        int nbBillets
) {
}
