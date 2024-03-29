package com.example.jo.entities;

import com.example.jo.entities.enums.Etat;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "billets")
public class Billet {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id", nullable = false)
    private Epreuve epreuve;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_spectateur", referencedColumnName = "id", nullable = false)
    private Spectateur spectateur;
    @Column(nullable = false)
    private double prix;
    @Column(nullable = false, columnDefinition = "ENUM('valide', 'annule', 'deja_utilise') DEFAULT 'valide'")
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
