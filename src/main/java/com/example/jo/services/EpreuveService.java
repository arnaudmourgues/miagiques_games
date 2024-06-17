package com.example.jo.services;

import com.example.jo.entities.DTOs.EpreuveDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.InfrastructureSportive;
import com.example.jo.entities.Participant;
import com.example.jo.entities.enums.Status;
import com.example.jo.errors.exceptions.ForfeitException;
import com.example.jo.repositories.EpreuveRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;
    private final ParticipationService participationService;
    private final AuthUserService authUserService;
    private final InfrastructureSportiveService infrastructureSportiveService;

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

    public Epreuve getEpreuveById(@NotNull UUID epreuveid) {
        return epreuveRepository.findById(epreuveid).orElseThrow(() -> new IllegalArgumentException("L'épreuve n'existe pas."));
    }

    public void createEpreuve(@NotNull EpreuveDto data) {
        InfrastructureSportive infrastructureSportive =
                infrastructureSportiveService.getInfrastructureSportiveById(UUID.fromString(data.infrastructureSportiveId()));
        if(infrastructureSportive == null) {
            throw new IllegalArgumentException("L'infrastructure sportive n'existe pas.");
        }
        if(data.nbPlacesSpectateurs() > infrastructureSportive.getCapacite()) {
            throw new IllegalArgumentException("Le nombre de places spectatrices est supérieur à celui de l'infrastructure sportive.");
        }
        Instant i = new Date(data.date().year() - 1900, data.date().month()-1, data.date().day(), data.date().hour(), data.date().minute(), 0).toInstant();
        Epreuve epreuve = new Epreuve();
        epreuve.setNom(data.nom());
        epreuve.setDate(i);
        epreuve.setNbPlacesParticipants(data.nbPlacesParticipants());
        epreuve.setNbPlacesSpectateurs(data.nbPlacesSpectateurs());
        epreuve.setInfrastructureSportive(infrastructureSportive);
        epreuveRepository.save(epreuve);
    }

    public void updateEpreuve(EpreuveDto data, UUID uuid) {
        Date date = new Date(data.date().year() - 1900, data.date().month()-1, data.date().day(), data.date().hour(), data.date().minute(), 0);
        System.out.println(date);
        Instant i = date.toInstant();
        System.out.println(i);
        Epreuve epreuve = getEpreuveById(uuid);
        epreuve.setNom(data.nom());
        epreuve.setDate(i);
        epreuve.setNbPlacesParticipants(data.nbPlacesParticipants());
        epreuve.setNbPlacesSpectateurs(data.nbPlacesSpectateurs());
        epreuveRepository.save(epreuve);
    }
}
