package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.repositories.UserRespository;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    private final EpreuveService epreuveService;
    private final UserRespository userRepository;

    public ParticipantService(EpreuveService epreuveService, UserRespository userRepository) {
        this.epreuveService = epreuveService;
        this.userRepository = userRepository;
    }

    public void inscrireEpreuve(Epreuve epreuve, Participant participant) {
        epreuveService.inscrireEpreuve(epreuve, participant);
    }

    public void desinscrireEpreuve(Epreuve epreuve, Participant participant) {
        epreuveService.desinscrireEpreuve(epreuve, participant);
    }
}
