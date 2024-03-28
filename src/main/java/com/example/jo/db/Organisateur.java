package com.example.jo.db;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("o")
public class Organisateur extends User {
}
