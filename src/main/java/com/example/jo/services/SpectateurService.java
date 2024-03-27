package com.example.jo.services;

import com.example.jo.db.entities.Spectateur;
import com.example.jo.repositories.SpectateurRepository;
import org.springframework.stereotype.Service;

@Service
public class SpectateurService {
    private final SpectateurRepository spectateurRepository;

    public SpectateurService(SpectateurRepository spectateurRepository) {
        this.spectateurRepository = spectateurRepository;
    }
    
    public void createSpectateur(Spectateur spectateur){
        //check if spectateur already exists
        if(checkSpectateur(spectateur)){
            throw new IllegalStateException("Spectateur already exists");
        }
        else spectateurRepository.save(spectateur);
    }

    private boolean checkSpectateur(Spectateur spectateur) {
        return spectateurRepository.existsById(spectateur.getId());
    }
}
