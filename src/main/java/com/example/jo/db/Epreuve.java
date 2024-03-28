package com.example.jo.db;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "epreuves")
public class Epreuve {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_infrastructure_sportive", referencedColumnName = "id", nullable = false)
    private InfrastructureSportive infrastructureSportive;
    @Column(nullable = false)
    private int nbPlaces;
    @ManyToMany(mappedBy = "epreuves")
    private List<Participant> participant;

    public Epreuve() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InfrastructureSportive getInfrastructureSportive() {
        return infrastructureSportive;
    }

    public void setInfrastructureSportive(InfrastructureSportive infrastructureSportive) {
        this.infrastructureSportive = infrastructureSportive;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public void addParticipant(Participant participant) {
        this.participant.add(participant);
    }

    public void removeParticipant(Participant participant) {
        this.participant.remove(participant);
    }
}
