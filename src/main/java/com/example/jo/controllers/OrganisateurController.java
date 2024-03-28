package com.example.jo.controllers;

import com.example.jo.db.*;
import com.example.jo.services.OrganisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class OrganisateurController {
    private final OrganisateurService organisateurService;

    public OrganisateurController(OrganisateurService organisateurService) {
        this.organisateurService = organisateurService;
    }

    @PostMapping("/connect")
    @ResponseStatus(HttpStatus.OK)
    public Organisateur connectOrganisateur(@RequestBody Organisateur user){
        return organisateurService.connect(user);
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

    @PostMapping("/createParticipant")
    @ResponseStatus(HttpStatus.CREATED)
    public void createParticipant(@RequestBody Participant user){
        organisateurService.createParticipant(user);
    }

    @PostMapping("/deleteParticipant")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParticipant(@RequestBody Participant user){
        organisateurService.deleteParticipant(user);
    }

    @PostMapping("/createControleur")
    @ResponseStatus(HttpStatus.CREATED)
    public void createControleur(@RequestBody Controleur user){
        organisateurService.createControleur(user);
    }

    @PostMapping("/deleteControleur")
    @ResponseStatus(HttpStatus.OK)
    public void deleteControleur(@RequestBody Controleur user){
        organisateurService.deleteControleur(user);
    }

}
