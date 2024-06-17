package com.example.jo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "delegations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Delegation {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nom;
    private int nbMedaillesOr;
    private int nbMedaillesArgent;
    private int nbMedaillesBronze;
    @OneToMany(mappedBy = "delegation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Participant> participants;

    public Delegation(String nom) {
        this.nom = nom;
        this.nbMedaillesOr = 0;
        this.nbMedaillesArgent = 0;
        this.nbMedaillesBronze = 0;
    }
}
