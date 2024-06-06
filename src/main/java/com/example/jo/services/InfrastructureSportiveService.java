package com.example.jo.services;

import com.example.jo.entities.DTOs.InfrastructureSportiveDto;
import com.example.jo.entities.InfrastructureSportive;
import com.example.jo.repositories.InfrastructureSportiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InfrastructureSportiveService {
    private final InfrastructureSportiveRepository infrastructureSportiveRepository;

    public void createInfrastructureSportive(InfrastructureSportiveDto data){
        InfrastructureSportive infrastructureSportive = new InfrastructureSportive();
        infrastructureSportive.setNom(data.nom());
        infrastructureSportive.setAdresse(data.adresse());
        infrastructureSportive.setCapacite(Integer.parseInt(data.capacite()));
        infrastructureSportiveRepository.save(infrastructureSportive);
    }

    public void deleteInfrastructureSportive(UUID infrastructureSportiveId) {
        infrastructureSportiveRepository.deleteById(infrastructureSportiveId);
    }

    public InfrastructureSportive getInfrastructureSportiveById(UUID infrastructureSportiveId) {
        return infrastructureSportiveRepository.findById(infrastructureSportiveId).orElse(null);
    }
}
