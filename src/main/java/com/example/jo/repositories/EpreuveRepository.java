package com.example.jo.repositories;

import com.example.jo.db.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, UUID> {
}
