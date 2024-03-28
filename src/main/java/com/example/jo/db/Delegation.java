package com.example.jo.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "delegation")
public class Delegation {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nom;
    private int nbMedaillesOr;
    private int nbMedaillesArgent;
    private int nbMedaillesBronze;

}
