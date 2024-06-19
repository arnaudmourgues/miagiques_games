package com.example.jo.services;

import com.example.jo.entities.DTOs.DelegationDto;
import com.example.jo.entities.Delegation;
import com.example.jo.repositories.DelegationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DelegationService {
    private final DelegationRepository delegationRepository;

    public DelegationService(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

    public void createDelegation(DelegationDto delegation){
        Delegation newDelegation = new Delegation(delegation.nom());
        delegationRepository.save(newDelegation);
    }

    public void deleteDelegation(UUID delegationId) {
        delegationRepository.deleteById(delegationId);
    }

    public boolean isDelegationExist(UUID delegationId) {
        return delegationRepository.existsById(delegationId);
    }

    public void addMedal(Delegation delegation, int i) {
        switch (i) {
            case 1 -> delegation.setNbMedaillesOr(delegation.getNbMedaillesOr() + 1);
            case 2 -> delegation.setNbMedaillesArgent(delegation.getNbMedaillesArgent() + 1);
            case 3 -> delegation.setNbMedaillesBronze(delegation.getNbMedaillesBronze() + 1);
        }
        delegationRepository.save(delegation);
    }

    public Iterable<Delegation> findAllDelegationOrderByMedals() {
        return delegationRepository.findAllByOrderByNbMedaillesOrDescNbMedaillesArgentDescNbMedaillesBronzeDesc();
    }

    public Iterable<Delegation> findAllDelegation() {
        return delegationRepository.findAll();
    }

    public Delegation getDelegationById(UUID uuid) {
        return delegationRepository.findById(uuid).orElse(null);
    }
}
