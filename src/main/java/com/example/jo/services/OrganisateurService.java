package com.example.jo.services;

import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
import com.example.jo.entities.*;
import org.springframework.stereotype.Service;

@Service
public class OrganisateurService {
    private final UserService userService;
    private final DelegationService delegationService;
    private final EpreuveService epreuveService;
    private final AuthService authService;

    public OrganisateurService(UserService userService, DelegationService delegationService, EpreuveService epreuveService, AuthService authService) {
        this.userService = userService;
        this.delegationService = delegationService;
        this.epreuveService = epreuveService;
        this.authService = authService;
    }

    public void createUser(SignUpUserDto data) {
        authService.signUpUser(data);
    }

    public void createModerateur(SignUpDto data) {
        authService.signUp(data);
    }

    public void createDelegation(Delegation delegation) {
        delegationService.createDelegation(delegation);
    }

    public void deleteDelegation(Delegation delegation) {
        delegationService.deleteDelegation(delegation);
    }

    public void deleteParticipant(Participant user) {
        userService.delete(user);
    }

    public void deleteControleur(Controleur user) {
        userService.delete(user);
    }

    public void createEpreuve(Epreuve epreuve) {
        epreuveService.createEpreuve(epreuve);
    }

    public void deleteEpreuve(Epreuve epreuve) {
        epreuveService.deleteEpreuve(epreuve);
    }

}
