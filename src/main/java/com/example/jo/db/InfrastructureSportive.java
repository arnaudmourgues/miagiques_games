package com.example.jo.db;

import jakarta.persistence.*;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
