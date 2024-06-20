package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Participant extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_delegation", referencedColumnName = "id")
    @JsonBackReference
    private Delegation delegation;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Participation> participations;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resultat> resultats;

    public Participant(String login, String encryptedPassword, UserRole userRole) {
        super(login, encryptedPassword, userRole);
    }

    public Participant(String login, String encryptedPassword, Delegation delegation, String nom, String prenom) {
        super(login, encryptedPassword, nom, prenom, UserRole.PARTICIPANT);
        this.delegation = delegation;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
