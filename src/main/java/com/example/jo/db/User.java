package com.example.jo.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance
@DiscriminatorColumn(name = "type_users", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public abstract class User {
    //to check https://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
