package com.example.jo.services;

import com.example.jo.entities.DTOs.EpreuveDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.InfrastructureSportive;
import com.example.jo.repositories.EpreuveRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;
    private final InfrastructureSportiveService infrastructureSportiveService;

    public void deleteEpreuve(UUID epreuveId) {
        epreuveRepository.deleteById(epreuveId);
    }

    public Iterable<Epreuve> getAllEpreuvesFuture() {
        return epreuveRepository.findAllByDateIsAfterToday(Instant.now());
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

    public Iterable<Epreuve> getAllEpreuvesPast() {
        return epreuveRepository.findAllByDateIsBeforeToday(Instant.now());
    }
}
