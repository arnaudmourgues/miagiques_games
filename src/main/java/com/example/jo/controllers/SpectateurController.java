package com.example.jo.controllers;

import com.example.jo.DTOs.SignUpUserDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Spectateur;
import com.example.jo.services.AuthService;
import com.example.jo.services.SpectateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spectateur")
public class SpectateurController {
    private final SpectateurService spectateurService;
    private final AuthService authService;

    public SpectateurController(SpectateurService spectateurService, AuthService authService) {
        this.spectateurService = spectateurService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpUserDto data) {
        authService.signUpUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/delete")
    public void deleteSpectateur(@RequestBody Spectateur spectateur){
        spectateurService.deleteSpectateur(spectateur);
    }

    @PostMapping("/connect")
    public void connectSpectateur(@RequestBody Spectateur spectateur){
        spectateurService.connectSpectateur(spectateur);
    }

    @PostMapping("/acheter")
    public void acheterBillet(Epreuve epreuve){
        spectateurService.acheterBillet(epreuve);
    }
}
