package com.example.jo.controllers;

import com.example.jo.entities.DTOs.DelegationDto;
import com.example.jo.entities.Delegation;
import com.example.jo.services.DelegationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class DelegationController {
    private final DelegationService delegationService;
    private final ModelMapper modelMapper;

    @PostMapping("/admin/createDelegation")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDelegation(@RequestBody DelegationDto delegation) {
        delegationService.createDelegation(modelMapper.map(delegation, Delegation.class));
    }

    @DeleteMapping("/admin/delegation/{delegationId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void deleteDelegation(@PathVariable String delegationId) {
        delegationService.deleteDelegation(UUID.fromString(delegationId));
    }

    @GetMapping("/getClassmentDelegation")
    public Iterable<Delegation> getClassmentDelegation() {
        return delegationService.findAllDelegationOrderByMedals();
    }

    @GetMapping("/delegation")
    @CrossOrigin
    public Iterable<Delegation> getDelegation() {
        return delegationService.findAllDelegation();
    }
}
