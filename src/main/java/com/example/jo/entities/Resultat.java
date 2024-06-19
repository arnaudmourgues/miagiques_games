package com.example.jo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "resultats")
@Setter
@Getter
public class Resultat {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id", nullable = false)
    private Epreuve epreuve;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false)
    private Participant participant;

    @Column
    private String temps;

    @Column(nullable = false)
    private int position;
}
