package com.example.jo.controllers;

import com.example.jo.entities.Billet;
import com.example.jo.entities.DTOs.BilletDto;
import com.example.jo.services.BilletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
public class BilletController {
    private final BilletService billetService;

    @PostMapping("/billeterie/acheter-billet")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_SPECTATEUR')")
    public ResponseEntity<HttpStatus> buyBillet(@RequestBody BilletDto data) {
        billetService.createBillet(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/billeterie/vente-billet/{epreuveId}")
    @ResponseStatus(HttpStatus.OK)
    public double sellBillets(@PathVariable String epreuveId) {
        return billetService.cancelBillet(UUID.fromString(epreuveId));
    }

    @DeleteMapping("/billeterie/vente-billet/billet/{billetId}")
    @ResponseStatus(HttpStatus.OK)
    public double sellOneBillet(@PathVariable String billetId) {
        return billetService.sellOneBillet(UUID.fromString(billetId));
    }

    @PutMapping("/controleur/billet/{billetId}")
    @PreAuthorize("hasRole('ROLE_CONTROLEUR')")
    public ResponseEntity<HttpStatus> controlBillet(@PathVariable String billetId) {
        billetService.controlBillet(UUID.fromString(billetId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/billets")
    @PreAuthorize("hasRole('ROLE_SPECTATEUR')")
    public Iterable<Billet> getBillets() {
        return billetService.getBilletsBySpectateur();
    }

}
