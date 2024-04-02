package com.example.jo.entities;

import com.example.jo.entities.enums.Etat;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "billets")
@Getter
public class Billet {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id", nullable = false)
    private Epreuve epreuve;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_spectateur", referencedColumnName = "id", nullable = false)
    private User spectateur;
    @Column(nullable = false)
    private double prix;
    @Column(nullable = false)
    private Etat etat;

    public Billet() {
        etat = Etat.VALIDE;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
