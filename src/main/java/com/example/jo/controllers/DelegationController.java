package com.example.jo.controllers;

import com.example.jo.entities.DTOs.DelegationDto;
import com.example.jo.entities.Delegation;
import com.example.jo.services.DelegationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
public class DelegationController {
    private final DelegationService delegationService;

    @PostMapping("/admin/delegation")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createDelegation(@RequestBody DelegationDto delegation) {
        delegationService.createDelegation(delegation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/admin/delegation/{delegationId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDelegation(@PathVariable String delegationId) {
        delegationService.deleteDelegation(UUID.fromString(delegationId));
    }

    @GetMapping("/delegation/classement")
    public Iterable<Delegation> getClassmentDelegation() {
        return delegationService.findAllDelegationOrderByMedals();
    }

    @GetMapping("/delegation/delegations")
    public Iterable<Delegation> getDelegations() {
        return delegationService.findAllDelegation();
    }
}
