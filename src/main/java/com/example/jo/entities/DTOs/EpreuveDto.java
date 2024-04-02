package com.example.jo.entities.DTOs;

import java.time.Instant;
import java.util.UUID;

public record EpreuveDto(
        String nom,
        Instant date,
        UUID infrastructureSportive,
        int nbPlaces
) {
}
