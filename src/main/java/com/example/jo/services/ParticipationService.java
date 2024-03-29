package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.Participation;
import com.example.jo.entities.enums.Status;
import com.example.jo.repositories.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public void addParticipation(Participant participant, Epreuve epreuve) {
        Participation participation = new Participation();
        participation.setParticipant(participant);
        participation.setEpreuve(epreuve);
        participationRepository.save(participation);
    }

    public List<Participation> getParticipantsByEpreuve(Epreuve epreuve) {
        return participationRepository.findAllByEpreuve(epreuve);
    }

    public boolean isParticipantInEpreuve(Participant participant, Epreuve epreuve) {
        return participationRepository.findByParticipantAndEpreuve(participant, epreuve) !=null;
    }

    public void updateStatus(Participant participant, Epreuve epreuve, Status status) {
        Participation participation = participationRepository.findByParticipantAndEpreuve(participant, epreuve);
        participation.setStatus(status);
        participationRepository.save(participation);
    }

    public void deleteParticipation(Participant participant, Epreuve epreuve) {
        Participation participation = participationRepository.findByParticipantAndEpreuve(participant, epreuve);
        if(participation == null) {
            throw new IllegalArgumentException("La participation n'existe pas");
        }
        participationRepository.delete(participation);
    }
}
