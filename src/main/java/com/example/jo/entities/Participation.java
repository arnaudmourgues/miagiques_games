package com.example.jo.entities;

import com.example.jo.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Participation {
    @Id
    @GeneratedValue
    private UUID id;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "id_epreuve", referencedColumnName = "id", nullable = false)
    private Epreuve epreuve;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false)
    private Participant participant;
}
