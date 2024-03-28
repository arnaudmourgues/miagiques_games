package com.example.jo.db;

import jakarta.persistence.*;

@Table(name = "resultats")
public class Resultat {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id", nullable = false)
    private Epreuve epreuve;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false)
    private Participant participant;
}
