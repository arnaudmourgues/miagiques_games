package com.example.jo.db.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("c")
public class Controleur extends User {
}
