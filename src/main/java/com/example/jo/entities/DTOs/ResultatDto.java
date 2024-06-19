package com.example.jo.entities.DTOs;

import java.util.List;
import java.util.UUID;

public record ResultatDto(
        UUID epreuveId,
        List<UUID> participantIds,
        List<Double> temps
) {
}
