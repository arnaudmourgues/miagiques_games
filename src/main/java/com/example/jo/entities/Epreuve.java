package com.example.jo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "epreuves")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Epreuve {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private Instant date;
    @ManyToOne
    @JoinColumn(name = "id_infrastructure_sportive", referencedColumnName = "id", nullable = false)
    private InfrastructureSportive infrastructureSportive;
    @Column(nullable = false)
    private int nbPlacesSpectateurs;
    @Column(nullable = false)
    private int nbPlacesParticipants;

    @OneToMany(mappedBy = "epreuve", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Participation> participations;

    @OneToMany(mappedBy = "epreuve", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resultat> resultats;
}
