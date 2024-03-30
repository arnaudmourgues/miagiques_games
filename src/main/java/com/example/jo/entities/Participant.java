package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Participant extends User {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_delegation", referencedColumnName = "id")
    private Delegation delegation;

    public Participant(String login, String encryptedPassword, UserRole userRole) {
        super(login, encryptedPassword, userRole);
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

}
