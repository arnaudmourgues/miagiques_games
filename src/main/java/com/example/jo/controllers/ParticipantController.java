package com.example.jo.controllers;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.services.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/participant/epreuve")
    @ResponseStatus(HttpStatus.OK)
    public void inscrireEpreuve(@RequestBody Epreuve epreuve, @RequestBody Participant participant) {
        participantService.inscrireEpreuve(epreuve, participant);
    }

    @PostMapping("/participant/desinscrireEpreuve")
    @ResponseStatus(HttpStatus.OK)
    public void desinscrireEpreuve(@RequestBody Epreuve epreuve, @RequestBody Participant participant) {
        participantService.desinscrireEpreuve(epreuve, participant);
    }
}
