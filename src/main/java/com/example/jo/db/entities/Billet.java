package com.example.jo.db.entities;

import com.example.jo.services.SpectateurService;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Billet {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID idEpreuve;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSpectateur", referencedColumnName = "id")
    private Spectateur idSpectateur;
    private double prix;
    private Etat etat;

    public Billet() {
        etat = Etat.VALIDE;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
