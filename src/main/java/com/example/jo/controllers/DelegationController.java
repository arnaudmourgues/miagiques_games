package com.example.jo.controllers;

import com.example.jo.entities.Delegation;
import com.example.jo.services.DelegationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class DelegationController {
    private final DelegationService delegationService;

    public DelegationController(DelegationService delegationService) {
        this.delegationService = delegationService;
    }

    @PostMapping("/createDelegation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDelegation(@RequestBody Delegation delegation){
        delegationService.createDelegation(delegation);
    }

    @PostMapping("/deleteDelegation")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDelegation(@RequestBody Delegation delegation){
        delegationService.deleteDelegation(delegation);
    }
}
