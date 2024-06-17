package com.example.jo.controllers;

import com.example.jo.services.ParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ParticipationController {
    private final ParticipationService participationService;

    @PostMapping("/participation/{epreuveId}")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public void inscription(@PathVariable String epreuveId) {
        participationService.addParticipation(UUID.fromString(epreuveId));
    }

    @DeleteMapping("/participation/{epreuveId}")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public void desinscrireEpreuve(@PathVariable String epreuveId) {
        participationService.deleteParticipation(UUID.fromString(epreuveId));
    }

    @GetMapping("/nbParticiptionByEpreuve/{epreuveId}")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public int getParticipantsByEpreuve(@PathVariable String epreuveId) {
        return participationService.getParticipantsByEpreuve(UUID.fromString(epreuveId)).size();
    }
}
