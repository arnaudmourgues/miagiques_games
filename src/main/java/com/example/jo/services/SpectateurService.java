package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Spectateur;
import com.example.jo.entities.User;
import org.springframework.stereotype.Service;

@Service
public class SpectateurService {
    private final UserService userService;
    private final BilletService billetService;

    public SpectateurService(UserService userService, BilletService billetService) {
        this.userService = userService;
        this.billetService = billetService;
    }
    
    public void createSpectateur(Spectateur spectateur){
        userService.create(spectateur);
    }

    private boolean checkSpectateur(Spectateur spectateur) {
        return userService.check(spectateur);
    }

    public void deleteSpectateur(Spectateur spectateur) {
        userService.delete(spectateur);
    }

    public void connectSpectateur(Spectateur spectateur) {
        if (checkSpectateur(spectateur)) {
            userService.connect(spectateur);
        }
    }

    public void acheterBillet(Epreuve epreuve) {
        billetService.createBillet(epreuve);
    }
}
