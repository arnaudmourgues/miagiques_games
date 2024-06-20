package com.example.jo.services;

import com.example.jo.entities.DTOs.ResultatDto;
import com.example.jo.entities.DTOs.ResultatsDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.Resultat;
import com.example.jo.repositories.ResultatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResultatService {
    private final ResultatRepository resultatRepository;
    private final ParticipationService participationService;
    private final UserService userService;
    private final EpreuveService epreuveService;
    private final DelegationService delegationService;

    public void publiateResultat(ResultatDto data) {
        Epreuve epreuve = epreuveService.getEpreuveById(data.epreuveId());
        //if epreuve is in the future throw exception
        if (epreuve.getDate().isAfter(Instant.now())) {
            throw new IllegalArgumentException("L'épreuve n'est pas encore passée.");
        }
        boolean[] verif = new boolean[data.resultats().size()];
        for (int i = 0; i < verif.length; i++) {
            verif[i] = false;
        }
        for (ResultatsDto result : data.resultats()) {
            Participant participant = userService.getParticipantById(UUID.fromString(result.participantId()));
            if (participant == null || !participationService.isParticipantInEpreuve(participant, epreuve)) {
                throw new IllegalArgumentException("L'utilisateur n'existe pas ou n'est pas inscrit à cette épreuve");
            }
            if (resultatRepository.findByParticipantAndEpreuve(participant, epreuve) != null) {
                throw new IllegalArgumentException("Les résultats ont déjà été rentrés.");
            }
            if (verif[result.position() - 1]) {
                throw new IllegalArgumentException("Erreur dans l'attrbution des positions, duplication de la position " + result.position());
            }
            verif[result.position() - 1] = true;
        }
        if (!isPositionsCorrect(verif)) {
            throw new IllegalArgumentException("Erreur dans l'attribution des positions, des positions sont manquantes.");
        }
        for (ResultatsDto result : data.resultats()) {
            Participant participant = userService.getParticipantById(UUID.fromString(result.participantId()));
            Resultat resultat = new Resultat();
            resultat.setParticipant(participant);
            resultat.setEpreuve(epreuve);
            resultat.setTemps(result.temps());
            resultat.setPosition(result.position());
            delegationService.addMedal(participant.getDelegation(), result.position());
            resultatRepository.save(resultat);
        }
    }

    private boolean isPositionsCorrect(boolean[] verif) {
        for (boolean b : verif) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public List<Resultat> getResultatsByParticipant() {
        Participant participant = (Participant) userService.getAuthenticatedUser();
        return resultatRepository.findByParticipant(participant);
    }
}
