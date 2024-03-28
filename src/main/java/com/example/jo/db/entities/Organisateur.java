package com.example.jo.db.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("o")
public class Organisateur extends User {
}
