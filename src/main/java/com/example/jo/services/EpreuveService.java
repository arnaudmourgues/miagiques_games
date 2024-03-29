package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.enums.Status;
import com.example.jo.repositories.EpreuveRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;
    private final ParticipationService participationService;

    public EpreuveService(EpreuveRepository epreuveRepository, ParticipationService participationService) {
        this.epreuveRepository = epreuveRepository;
        this.participationService = participationService;
    }

    public void createEpreuve(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }

    public void deleteEpreuve(Epreuve epreuve) {
        epreuveRepository.delete(epreuve);
    }

    public Iterable<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    public void inscrireEpreuve(@NotNull Epreuve epreuve, @NotNull Participant participant) {
        if (!participationService.isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant participe déjà à l'épreuve");
        } else if (participationService.getParticipantsByEpreuve(epreuve).size() >= epreuve.getNbPlaces()) {
            throw new IllegalArgumentException("L'épreuve est déjà pleine");
        }
        //si l'épreuve est dans moins de 10 jours, on ne peut plus s'inscrire
        else if (Instant.now().plusSeconds(10 * 24 * 60 * 60).isAfter(epreuve.getDate())) {
            throw new IllegalArgumentException("L'épreuve est dans moins de 10 jours, vous ne pouvez plus vous inscrire");
        }
        participationService.addParticipation(participant, epreuve);
    }

    public void desinscrireEpreuve(Epreuve epreuve, Participant participant) {
        if (!participationService.isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant ne participe pas à l'épreuve");
        } else if (Instant.now().plusSeconds(10 * 24 * 60 * 60).isAfter(epreuve.getDate())) {
            participationService.updateStatus(participant, epreuve, Status.FORFAIT);
        } else {
            participationService.deleteParticipation(participant, epreuve);
        }
    }
}
