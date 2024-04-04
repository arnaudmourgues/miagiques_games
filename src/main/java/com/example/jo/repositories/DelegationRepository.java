package com.example.jo.repositories;

import com.example.jo.entities.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DelegationRepository extends JpaRepository<Delegation, UUID> {
    @Query("SELECT d FROM Delegation d ORDER BY d.nbMedaillesOr DESC, d.nbMedaillesArgent DESC, d.nbMedaillesBronze DESC")
    Iterable<Delegation> findAllByOrderByNbMedaillesOrDescNbMedaillesArgentDescNbMedaillesBronzeDesc();
}
