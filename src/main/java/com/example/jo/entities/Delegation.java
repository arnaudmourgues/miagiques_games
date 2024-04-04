package com.example.jo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "delegations")
@Setter
@Getter
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
