package com.example.jo.repositories;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    List<Participation> findAllByEpreuve(Epreuve epreuve);
    @Query("SELECT p FROM Participation p WHERE p.participant = ?1 AND p.epreuve = ?2")
    Participation findByParticipantAndEpreuve(Participant participant, Epreuve epreuve);
    Iterable<Participation> findAllByParticipant(Participant participant);
}
