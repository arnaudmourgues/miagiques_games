package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Controleur extends User {
    public Controleur(String login, String encryptedPassword, UserRole role) {
        super(login, encryptedPassword, role);
    }

    public Controleur() {

    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
