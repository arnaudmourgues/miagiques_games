package com.example.jo.entities.DTOs;

import java.util.UUID;

public record ResultatDto(
        UUID epreuveId,
        UUID participantId,
        int position,
        double temps
) {
}
