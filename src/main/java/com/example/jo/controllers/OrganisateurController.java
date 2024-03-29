package com.example.jo.controllers;

import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.config.auth.TokenProvider;
import com.example.jo.entities.*;
import com.example.jo.services.AuthService;
import com.example.jo.services.OrganisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class OrganisateurController {
    private final OrganisateurService organisateurService;

    public OrganisateurController(OrganisateurService organisateurService, AuthenticationManager authenticationManager, AuthService service, TokenProvider tokenService) {
        this.organisateurService = organisateurService;
    }

    @PostMapping("/createModerateur")
    @ResponseStatus(HttpStatus.CREATED)
    public void createModerateur(@RequestBody SignUpDto data){
        organisateurService.createModerateur(data);
    }

    @PostMapping("/createDelegation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDelegation(@RequestBody Delegation delegation){
        organisateurService.createDelegation(delegation);
    }

    @PostMapping("/deleteDelegation")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDelegation(@RequestBody Delegation delegation){
        organisateurService.deleteDelegation(delegation);
    }
    @PostMapping("/deleteParticipant")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParticipant(@RequestBody Participant user){
        organisateurService.deleteParticipant(user);
    }

    @PostMapping("/deleteControleur")
    @ResponseStatus(HttpStatus.OK)
    public void deleteControleur(@RequestBody Controleur user){
        organisateurService.deleteControleur(user);
    }

    @PostMapping("/createEpreuve")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEpreuve(@RequestBody Epreuve epreuve){
        organisateurService.createEpreuve(epreuve);
    }

    @PostMapping("/deleteEpreuve")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEpreuve(@RequestBody Epreuve epreuve){
        organisateurService.deleteEpreuve(epreuve);
    }
}
