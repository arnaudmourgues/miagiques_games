package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.Participation;
import com.example.jo.entities.enums.Status;
import com.example.jo.errors.exceptions.ForfeitException;
import com.example.jo.repositories.ParticipationRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final EpreuveService epreuveService;
    private final AuthUserService authUserService;

    public void addParticipation(UUID epreuveId) {
        Epreuve epreuve = epreuveService.getEpreuveById(epreuveId);
        Participant participant = (Participant) authUserService.getAuthenticatedUser();
        if (isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant participe déjà à l'épreuve.");
        } else if (getParticipantsByEpreuve(epreuve.getId()).size() >= epreuve.getNbPlacesParticipants()) {
            throw new IllegalArgumentException("L'épreuve est déjà pleine.");
        }
        //si l'épreuve est dans moins de 10 jours, on ne peut plus s'inscrire
        else if (epreuve.getDate().isBefore(Instant.now().plusSeconds(10 * 24 * 60 * 60))) {
            throw new IllegalArgumentException("L'épreuve est dans moins de 10 jours, vous ne pouvez plus vous inscrire.");
        }
        Participation participation = new Participation();
        participation.setParticipant(participant);
        participation.setEpreuve(epreuve);
        participationRepository.save(participation);
    }

    public void deleteParticipation(@NotNull UUID epreuveId) {
        Epreuve epreuve = epreuveService.getEpreuveById(epreuveId);
        Participant participant = (Participant) authUserService.getAuthenticatedUser();
        if (!isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant ne participe pas à l'épreuve.");
        } else if (epreuve.getDate().isBefore(Instant.now().plusSeconds(10 * 24 * 60 * 60))) {
            updateStatus(participant, epreuve, Status.FORFAIT);
            throw new ForfeitException("L'épreuve est dans moins de 10 jours, vous êtes forfait pour l'épreuve.");
        } else {
            Participation participation = participationRepository.findByParticipantAndEpreuve(participant, epreuve);
            if(participation == null) {
                throw new IllegalArgumentException("La participation n'existe pas");
            }
            participationRepository.delete(participation);
        }
    }

    public List<Participation> getParticipantsByEpreuve(UUID epreuveId) {
        Epreuve epreuve = epreuveService.getEpreuveById(epreuveId);
        return participationRepository.findAllByEpreuve(epreuve);
    }

    public boolean isParticipantInEpreuve(Participant participant, Epreuve epreuve) {
        return participationRepository.findByParticipantAndEpreuve(participant, epreuve) != null;
    }

    public void updateStatus(Participant participant, Epreuve epreuve, Status status) {
        Participation participation = participationRepository.findByParticipantAndEpreuve(participant, epreuve);
        participation.setStatus(status);
        participationRepository.save(participation);
    }
}
