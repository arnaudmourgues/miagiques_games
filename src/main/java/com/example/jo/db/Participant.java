package com.example.jo.db;

import jakarta.persistence.*;

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
