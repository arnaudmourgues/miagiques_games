package com.example.jo.controllers;

import com.example.jo.entities.DTOs.EpreuveDto;
import com.example.jo.entities.DTOs.UpdateEpreuveDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.services.EpreuveService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
public class EpreuveController {
    private final EpreuveService epreuveService;

    @PostMapping("/admin/createEpreuve")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void createEpreuve(@RequestBody EpreuveDto data) {
        epreuveService.createEpreuve(data);
    }

    @DeleteMapping("/admin/deleteEpreuve")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void deleteEpreuve(@RequestBody UUID epreuveId) {
        epreuveService.deleteEpreuve(epreuveId);
    }

    @PutMapping("/admin/updateEpreuve")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void updateEpreuve(@RequestBody UpdateEpreuveDto data) {
        epreuveService.updateEpreuve(data);
    }

    @PostMapping("/participation/inscrireEpreuve")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public void inscrireEpreuve(@RequestBody UUID epreuveId) {
        epreuveService.inscrireEpreuve(epreuveId);
    }

    @DeleteMapping("/participation/desinscrireEpreuve")
    @PreAuthorize("hasRole('ROLE_PARTICIPANT')")
    public void desinscrireEpreuve(@RequestBody UUID epreuveId) {
        epreuveService.desinscrireEpreuve(epreuveId);
    }

    @GetMapping("/getAllEpreuves")
    public Iterable<Epreuve> getAllEpreuves() {
        return epreuveService.getAllEpreuves();
    }
}
