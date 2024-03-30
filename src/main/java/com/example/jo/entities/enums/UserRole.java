package com.example.jo.entities.enums;

import com.example.jo.entities.Participant;

public enum UserRole {
    ORGANISATEUR("organisateur"),
    CONTROLEUR("controleur"),
    SPECTATEUR("spectateur"),
    PARTICIPANT("participant");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
