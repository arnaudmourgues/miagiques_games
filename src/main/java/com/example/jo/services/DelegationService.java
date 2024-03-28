package com.example.jo.services;

import com.example.jo.db.Delegation;
import com.example.jo.repositories.DelegationRepository;
import org.springframework.stereotype.Service;

@Service
public class DelegationService {
    private final DelegationRepository delegationRepository;

    public DelegationService(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

    public void createDelegation(Delegation delegation){
        delegationRepository.save(delegation);
    }

    public void deleteDelegation(Delegation delegation) {
        delegationRepository.delete(delegation);
    }
}
