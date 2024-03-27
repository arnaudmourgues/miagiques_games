package com.example.jo.db.entities;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

public abstract class Personne implements Serializable {
    @Id
    private UUID id;
    private String nom;
    private String prenom;
    private String email;

    public Personne() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
