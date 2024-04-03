package com.example.jo.controllers;

import com.example.jo.entities.DTOs.DelegationDto;
import com.example.jo.entities.Delegation;
import com.example.jo.services.DelegationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class DelegationController {
    private final DelegationService delegationService;
    private final ModelMapper modelMapper;

    @PostMapping("/createDelegation")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDelegation(@RequestBody DelegationDto delegation){
        delegationService.createDelegation(modelMapper.map(delegation, Delegation.class));
    }

    @PostMapping("/deleteDelegation")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDelegation(@RequestBody UUID delegationId){
        delegationService.deleteDelegation(delegationId);
    }
}
