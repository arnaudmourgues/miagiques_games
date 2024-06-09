package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Participant extends User {
    @ManyToOne
    @JoinColumn(name = "id_delegation", referencedColumnName = "id")
    private Delegation delegation;

    public Participant(String login, String encryptedPassword, UserRole userRole) {
        super(login, encryptedPassword, userRole);
    }

    public Participant(String login, String encryptedPassword, Delegation delegation, String nom, String prenom) {
        super(login, encryptedPassword, UserRole.PARTICIPANT, nom, prenom);
        this.delegation = delegation;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
