package com.example.jo.db;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("s")
public class Spectateur extends User {
}
