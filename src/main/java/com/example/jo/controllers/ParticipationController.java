package com.example.jo.controllers;

import com.example.jo.entities.Participation;
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

    @DeleteMapping("/participation/{participationId}")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public void desinscrireEpreuve(@PathVariable String participationId) {
        participationService.deleteParticipation(UUID.fromString(participationId));
    }

    @GetMapping("/nbParticiptionByEpreuve/{epreuveId}")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public int getNbParticipantsByEpreuve(@PathVariable String epreuveId) {
        return participationService.getParticipantsByEpreuve(UUID.fromString(epreuveId)).size();
    }

    @GetMapping("/participationParticipant")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public Iterable<Participation> getParticipationsByParticipant() {
        return participationService.getParticipationsByParticipant();
    }

    @GetMapping("/participation/epreuve?={epreuveId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public Iterable<Participation> getParticipationsByEpreuve(@PathVariable String epreuveId) {
        return participationService.getParticipationsByEpreuve(UUID.fromString(epreuveId));
    }
}
