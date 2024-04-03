package com.example.jo.services;

import com.example.jo.entities.InfrastructureSportive;
import com.example.jo.repositories.InfrastructureSportiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InfrastructureSportiveService {
    private final InfrastructureSportiveRepository infrastructureSportiveRepository;

    public void createInfrastructureSportive(InfrastructureSportive infrastructureSportive){
        infrastructureSportiveRepository.save(infrastructureSportive);
    }

    public void deleteInfrastructureSportive(UUID infrastructureSportiveId) {
        infrastructureSportiveRepository.deleteById(infrastructureSportiveId);
    }

    public boolean isInfrastructureSportiveExist(UUID infrastructureSportiveId) {
        return infrastructureSportiveRepository.existsById(infrastructureSportiveId);
    }

    public InfrastructureSportive getInfrastructureSportiveById(UUID infrastructureSportiveId) {
        return infrastructureSportiveRepository.findById(infrastructureSportiveId).orElse(null);
    }
}
