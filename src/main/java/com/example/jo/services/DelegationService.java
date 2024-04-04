package com.example.jo.services;

import com.example.jo.entities.Delegation;
import com.example.jo.repositories.DelegationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DelegationService {
    private final DelegationRepository delegationRepository;

    public DelegationService(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

    public void createDelegation(Delegation delegation){
        delegationRepository.save(delegation);
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
            default -> System.out.println("Pas de médaille pour cette position : " + i);
        }
        delegationRepository.save(delegation);
    }

    public Iterable<Delegation> findAllDelegationOrderByMedals() {
        return delegationRepository.findAllByOrderByNbMedaillesOrDescNbMedaillesArgentDescNbMedaillesBronzeDesc();
    }
}
