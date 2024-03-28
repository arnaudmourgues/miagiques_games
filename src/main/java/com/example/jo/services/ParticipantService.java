package com.example.jo.services;

import com.example.jo.db.Epreuve;
import com.example.jo.db.Participant;
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

    private void participerEpreuve(Epreuve epreuve, Participant participant) {
        epreuveService.participerEpreuve(epreuve, participant);
    }
}
