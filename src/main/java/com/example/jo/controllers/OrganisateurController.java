package com.example.jo.controllers;

import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.config.auth.TokenProvider;
import com.example.jo.entities.*;
import com.example.jo.entities.DTOs.UserDto;
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
}
