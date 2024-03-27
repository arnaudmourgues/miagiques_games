package com.example.jo.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Billet {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID idEpreuve;
    private UUID idSpectateur;
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
