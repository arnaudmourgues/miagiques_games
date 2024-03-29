package com.example.jo.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("p")
public class Participant extends User {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_delegation", referencedColumnName = "id")
    private Delegation delegation;

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public Delegation getDelegation() {
        return delegation;
    }

}
