package com.example.jo.repositories;

import com.example.jo.entities.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, UUID> {

    @Query("SELECT e FROM Epreuve e WHERE e.date < ?1")
    Iterable<Epreuve> findAllByDateIsBeforeToday(Instant instant);

    @Query("SELECT e FROM Epreuve e WHERE e.date >= ?1")
    Iterable<Epreuve> findAllByDateIsAfterToday(Instant now);
}
