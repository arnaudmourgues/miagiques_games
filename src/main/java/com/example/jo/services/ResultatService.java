package com.example.jo.services;

import com.example.jo.entities.DTOs.ResultatDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.Resultat;
import com.example.jo.repositories.ResultatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultatService {
    private final ResultatRepository resultatRepository;
    private final ParticipationService participationService;
    private final AuthUserService authUserService;
    private final EpreuveService epreuveService;
    private final DelegationService delegationService;

    public void publiateResultat(ResultatDto data) {
        Participant participant = (Participant) authUserService.getAuthenticatedUser();
        Epreuve epreuve = epreuveService.getEpreuveById(data.epreuveId());
        if(participant == null || epreuve == null) {
            throw new IllegalArgumentException("L'utilisateur ou l'épreuve n'existe pas");
        }
        if (!participationService.isParticipantInEpreuve(participant, epreuve)) {
            throw new IllegalArgumentException("Le participant n'est pas inscrit à cette épreuve");
        }
        if (resultatRepository.findByParticipantAndEpreuve(participant, epreuve) != null) {
            throw new IllegalArgumentException("Le résultat existe déjà");
        }
        Resultat resultat = new Resultat();
        resultat.setParticipant(participant);
        resultat.setEpreuve(epreuve);
        //resultat.setTemps(data.temps());
        //resultat.setPosition(data.position());
        //delegationService.addMedal(participant.getDelegation(), data.position());
        resultatRepository.save(resultat);
    }
}
