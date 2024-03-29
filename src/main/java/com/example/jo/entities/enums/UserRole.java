package com.example.jo.entities.enums;

public enum UserRole {
    ORGANISATEUR("admin"),
    CONTROLLEUR("controlleur"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
