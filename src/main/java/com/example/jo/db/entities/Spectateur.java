package com.example.jo.db.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("s")
public class Spectateur extends User {
}
