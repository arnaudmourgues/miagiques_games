package com.example.jo.repositories;

import com.example.jo.entities.Epreuve;
import com.example.jo.entities.Participant;
import com.example.jo.entities.Participation;
import com.example.jo.entities.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, UUID>{
    @Query("SELECT r FROM Resultat r WHERE r.participant = ?1 AND r.epreuve = ?2")
    Participation findByParticipantAndEpreuve(Participant participant, Epreuve epreuve);
}
