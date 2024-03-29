package com.example.jo.services;

import com.example.jo.entities.*;
import org.springframework.stereotype.Service;

@Service
public class OrganisateurService {
    private final UserService userService;
    private final DelegationService delegationService;
    private final EpreuveService epreuveService;

    public OrganisateurService(UserService userService, DelegationService delegationService, EpreuveService epreuveService) {
        this.userService = userService;
        this.delegationService = delegationService;
        this.epreuveService = epreuveService;
    }

    public Organisateur connect(Organisateur organisateur) {
        User user = userService.connect(organisateur);
        if (user instanceof Organisateur) {
            return (Organisateur) user;
        } else throw new IllegalStateException("User is not a Organisateur");
    }

    public void createDelegation(Delegation delegation) {
        delegationService.createDelegation(delegation);
    }

    public void deleteDelegation(Delegation delegation) {
        delegationService.deleteDelegation(delegation);
    }

    public void createParticipant(Participant user) {
        if(userService.checkByEmail(user)){
            throw new IllegalStateException("Le participant existe déjà");
        } if(user.getDelegation() == null){
            throw new IllegalStateException("Le participant doit appartenir à une délégation");
        }
        userService.create(user);
    }

    public void deleteParticipant(Participant user) {
        userService.delete(user);
    }

    public void createControleur(Controleur user) {
        if(userService.checkByEmail(user)){
            throw new IllegalStateException("User already exists");
        }
        userService.create(user);
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
