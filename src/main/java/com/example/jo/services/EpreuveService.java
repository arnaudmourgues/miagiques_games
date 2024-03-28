package com.example.jo.services;

import com.example.jo.db.Epreuve;
import com.example.jo.db.Participant;
import com.example.jo.repositories.EpreuveRepository;
import org.springframework.stereotype.Service;

@Service
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;

    public EpreuveService(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }

    public void createEpreuve(Epreuve epreuve){
        epreuveRepository.save(epreuve);
    }

    public void deleteEpreuve(Epreuve epreuve) {
        epreuveRepository.delete(epreuve);
    }

    public Iterable<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    public void participerEpreuve(Epreuve epreuve, Participant participant) {
        epreuve.setNbPlaces(epreuve.getNbPlaces() - 1);
        epreuve.addParticipant(participant);
        epreuveRepository.save(epreuve);
    }
}
