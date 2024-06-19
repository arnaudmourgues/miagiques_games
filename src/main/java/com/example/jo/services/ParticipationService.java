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
    private final UserService userService;

    public void addParticipation(UUID epreuveId) {
        Epreuve epreuve = epreuveService.getEpreuveById(epreuveId);
        Participant participant = (Participant) userService.getAuthenticatedUser();
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
        participation.setStatus(Status.INSCRIT);
        participationRepository.save(participation);
    }

    public void deleteParticipation(@NotNull UUID epreuveId) {
        Participation participation = participationRepository.findById(epreuveId).get();
        if (participation.getEpreuve().getDate().isBefore(Instant.now().plusSeconds(10 * 24 * 60 * 60))) {
            updateStatus(participation, Status.FORFAIT);
            throw new ForfeitException("L'épreuve est dans moins de 10 jours, vous êtes forfait pour l'épreuve.");
        }
        participationRepository.delete(participation);
    }

    public List<Participation> getParticipantsByEpreuve(UUID epreuveId) {
        Epreuve epreuve = epreuveService.getEpreuveById(epreuveId);
        return participationRepository.findAllByEpreuve(epreuve);
    }

    public boolean isParticipantInEpreuve(Participant participant, Epreuve epreuve) {
        return participationRepository.findByParticipantAndEpreuve(participant, epreuve) != null;
    }

    public void updateStatus(Participation p, Status status) {
        p.setStatus(status);
        participationRepository.save(p);
    }

    public Iterable<Participation> getParticipationsByParticipant() {
        Participant participant = (Participant) userService.getAuthenticatedUser();
        return participationRepository.findAllByParticipant(participant);
    }

    public Iterable<Participation> getParticipationsByEpreuve(UUID epreuveId) {
        Epreuve epreuve = epreuveService.getEpreuveById(epreuveId);
        return participationRepository.findAllByEpreuve(epreuve);
    }
}
