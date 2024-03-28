package com.example.jo.controllers;

import com.example.jo.db.entities.Spectateur;
import com.example.jo.db.entities.User;
import com.example.jo.services.SpectateurService;
import org.springframework.web.bind.annotation.PostMapping;

public class SpectateurController {
    private final SpectateurService spectateurService;

    public SpectateurController(SpectateurService spectateurService) {
        this.spectateurService = spectateurService;
    }

    @PostMapping("/createSpectateur")
    public void createSpectateur(Spectateur spectateur){
        spectateurService.createSpectateur(spectateur);
    }

    @PostMapping("/deleteSpectateur")
    public void deleteSpectateur(Spectateur spectateur){
        spectateurService.deleteSpectateur(spectateur);
    }

    @PostMapping("/connectSpectateur")
    public Spectateur connectSpectateur(Spectateur spectateur){
        return spectateurService.connectSpectateur(spectateur);
    }
}
