package com.example.jo.entities.DTOs;

public record SignUpParcipantDto(
        String email,
        String nom,
        String prenom,
        String password,
        String delegationid
) {
}
