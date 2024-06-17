package com.example.jo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "infrastructures_sportives")
public class InfrastructureSportive {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nom;
    private String adresse;
    @Column(nullable = false)
    private int capacite;

    public InfrastructureSportive() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
