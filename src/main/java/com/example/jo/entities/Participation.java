package com.example.jo.entities;

import com.example.jo.entities.enums.Status;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Participation {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(columnDefinition = "ENUM('inscrit', 'forfait') DEFAULT 'inscrit'")
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id", nullable = false)
    private Epreuve epreuve;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false)
    private Participant participant;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
