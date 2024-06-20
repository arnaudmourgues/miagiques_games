package com.example.jo.controllers;

import com.example.jo.entities.DTOs.ResultatDto;
import com.example.jo.entities.Resultat;
import com.example.jo.services.ResultatService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ResultatController {
    private final ResultatService resultatService;

    @PostMapping("/admin/resultat/publier-resultat")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void publiateResultat(@RequestBody ResultatDto data) {
        resultatService.publiateResultat(data);
    }

    @GetMapping("/resultat/resultats")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public List<Resultat> getResultatsByParticipant() {
        return resultatService.getResultatsByParticipant();
    }
}
