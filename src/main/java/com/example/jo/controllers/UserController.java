package com.example.jo.controllers;

import com.example.jo.entities.DTOs.StatDto;
import com.example.jo.entities.Participant;
import com.example.jo.services.UserService;
import com.example.jo.services.UserSubService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserSubService subService;

    @GetMapping("/admin/participant/participants")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public Iterable<Participant> getParticipants() {
        return userService.getParticipants();
    }

    @GetMapping("/admin/statistiques")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public StatDto getStatistiques() {
        return subService.getStatistiques();
    }

}
