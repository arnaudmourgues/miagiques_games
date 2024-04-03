package com.example.jo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "epreuves")
@Setter
@Getter
public class Epreuve {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private Instant date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_infrastructure_sportive", referencedColumnName = "id", nullable = false)
    private InfrastructureSportive infrastructureSportive;
    @Column(nullable = false)
    private int nbPlacesSpectateurs;
    @Column(nullable = false)
    private int nbPlacesParticipants;
}
