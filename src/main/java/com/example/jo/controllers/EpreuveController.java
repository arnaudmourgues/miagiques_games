package com.example.jo.controllers;

import com.example.jo.entities.DTOs.EpreuveDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.services.EpreuveService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
public class EpreuveController {
    private final EpreuveService epreuveService;

    @PostMapping("/admin/epreuve")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void createEpreuve(@RequestBody EpreuveDto data) {
        epreuveService.createEpreuve(data);
    }

    @DeleteMapping("/admin/epreuve/{epreuveId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void deleteEpreuve(@PathVariable String epreuveId) {
        epreuveService.deleteEpreuve(UUID.fromString(epreuveId));
    }

    @PutMapping("/admin/epreuve/{epreuveId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void updateEpreuve(@RequestBody EpreuveDto data, @PathVariable String epreuveId) {
        epreuveService.updateEpreuve(data, UUID.fromString(epreuveId));
    }

    @GetMapping("/epreuve")
    @PreAuthorize("hasRole('ROLE_SPECTATEUR')" +
            " or hasRole('ROLE_PARTICIPANT')")
    public Iterable<Epreuve> getAllEpreuves() {
        return epreuveService.getAllEpreuvesFuture();
    }

    @GetMapping("/admin/epreuve/epreuves-passees")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public Iterable<Epreuve> getAllEpreuvesAdmin() {
        return epreuveService.getAllEpreuvesPast();
    }
}
