package com.example.jo.repositories;

import com.example.jo.entities.Billet;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BilletRepository extends JpaRepository<Billet, UUID> {
    @Query("SELECT count(b) FROM Billet b WHERE b.epreuve = ?1")
    int countByEpreuve(Epreuve epreuve);

    List<Billet> findBySpectateurAndEpreuve(User user, Epreuve epreuve);
}
