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
}
