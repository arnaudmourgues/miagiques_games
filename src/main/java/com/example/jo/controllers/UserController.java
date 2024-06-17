package com.example.jo.controllers;

import com.example.jo.entities.Participant;
import com.example.jo.services.AuthUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private AuthUserService userService;

    @GetMapping("/participants")
    public Iterable<Participant> getParticipants() {
        return userService.getParticipants();
    }

}
