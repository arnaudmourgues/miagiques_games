package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Spectateur extends User {
    @OneToMany(mappedBy = "spectateur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Billet> billet;

    public Spectateur(String email, String encryptedPassword, String nom, String prenom, UserRole userRole) {
        super(email, encryptedPassword, nom, prenom, userRole);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
