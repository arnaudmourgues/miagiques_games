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

    @PostMapping("/cancelBillet")
    @ResponseStatus(HttpStatus.OK)
    public double cancelBillet(@RequestBody UUID billetId) {
        return billetService.cancelBillet(billetId);
    }

    @PostMapping("/controlBillet")
    @PreAuthorize("hasRole('ROLE_CONTROLEUR')")
    public ResponseEntity<Void> controlBillet(@RequestBody UUID billetId) {
        if(billetService.controlBillet(billetId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/billets")
    @PreAuthorize("hasRole('ROLE_SPECTATEUR')")
    public Iterable<Billet> getBillets() {
        return billetService.getBillets();
    }

}
