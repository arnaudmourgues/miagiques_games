package com.example.jo.controllers;

import com.example.jo.db.Spectateur;
import com.example.jo.services.SpectateurService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spectateur")
public class SpectateurController {
    private final SpectateurService spectateurService;

    public SpectateurController(SpectateurService spectateurService) {
        this.spectateurService = spectateurService;
    }

    @PostMapping("/create")
    public void createSpectateur(@RequestBody Spectateur spectateur){
        spectateurService.createSpectateur(spectateur);
    }

    @PostMapping("/delete")
    public void deleteSpectateur(@RequestBody Spectateur spectateur){
        spectateurService.deleteSpectateur(spectateur);
    }

    @PostMapping("/connect")
    public Spectateur connectSpectateur(@RequestBody Spectateur spectateur){
        return spectateurService.connectSpectateur(spectateur);
    }
}
