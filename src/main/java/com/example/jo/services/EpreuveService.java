package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.User;
import com.example.jo.entities.enums.Status;
import com.example.jo.errors.exceptions.ForfeitException;
import com.example.jo.repositories.EpreuveRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;
    private final ParticipationService participationService;
    private final AuthUserService authUserService;

    public EpreuveService(EpreuveRepository epreuveRepository, ParticipationService participationService, AuthUserService authUserService) {
        this.epreuveRepository = epreuveRepository;
        this.participationService = participationService;
        this.authUserService = authUserService;
    }

    public void createEpreuve(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }

    public void deleteEpreuve(UUID epreuveId) {
        epreuveRepository.deleteById(epreuveId);
    }

    public Iterable<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    public void inscrireEpreuve(@NotNull UUID epreuveId) {
        Epreuve epreuve = getEpreuveById(epreuveId);
        Participant participant = (Participant) authUserService.getAuthenticatedUser();
        if (participationService.isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant participe déjà à l'épreuve.");
        } else if (participationService.getParticipantsByEpreuve(epreuve).size() >= epreuve.getNbPlacesParticipants()) {
            throw new IllegalArgumentException("L'épreuve est déjà pleine.");
        }
        //si l'épreuve est dans moins de 10 jours, on ne peut plus s'inscrire
        else if (epreuve.getDate().isBefore(Instant.now().plusSeconds(10 * 24 * 60 * 60))) {
            throw new IllegalArgumentException("L'épreuve est dans moins de 10 jours, vous ne pouvez plus vous inscrire.");
        }
        participationService.addParticipation(participant, epreuve);
    }

    public void desinscrireEpreuve(@NotNull UUID epreuveId) {
        Epreuve epreuve = getEpreuveById(epreuveId);
        Participant participant = (Participant) authUserService.getAuthenticatedUser();
        if (!participationService.isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant ne participe pas à l'épreuve.");
        } else if (epreuve.getDate().isBefore(Instant.now().plusSeconds(10 * 24 * 60 * 60))) {
            participationService.updateStatus(participant, epreuve, Status.FORFAIT);
            throw new ForfeitException("L'épreuve est dans moins de 10 jours, vous êtes forfait pour l'épreuve.");
        } else {
            participationService.deleteParticipation(participant, epreuve);
        }
    }

    public Epreuve getEpreuveById(UUID epreuveid) {
        return epreuveRepository.findById(epreuveid).orElseThrow(() -> new IllegalArgumentException("L'épreuve n'existe pas."));
    }
}
