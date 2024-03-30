package com.example.jo.services;

import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
import com.example.jo.entities.*;
import com.example.jo.entities.DTOs.UserDto;
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
        authService.signUpSpectateur(data);
    }

    public void createByOrganisateur(SignUpDto data) {
        authService.signUpByOrganisateur(data);
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

    public void deleteUser(UserDto user) {
        User u = userService.findByUsername(user.username());
        if (u == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        userService.delete(u);
    }

    public void createEpreuve(Epreuve epreuve) {
        epreuveService.createEpreuve(epreuve);
    }

    public void deleteEpreuve(Epreuve epreuve) {
        epreuveService.deleteEpreuve(epreuve);
    }

}
