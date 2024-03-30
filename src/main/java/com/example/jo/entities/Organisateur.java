package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.*;

@Entity
public class Organisateur extends User {
    public Organisateur(String login, String encryptedPassword, UserRole role) {
        super(login, encryptedPassword, role);
    }

    public Organisateur() {

    }
}
