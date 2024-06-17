package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.*;

@Entity
public class Spectateur extends User {
    public Spectateur(String login, String encryptedPassword, UserRole role) {
        super(login, encryptedPassword, role);
    }

    public Spectateur() {

    }

    public Spectateur(String email, String encryptedPassword, String nom, String prenom, UserRole userRole) {
        super(email, encryptedPassword, nom, prenom, userRole);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
