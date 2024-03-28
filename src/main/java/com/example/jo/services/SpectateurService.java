package com.example.jo.services;

import com.example.jo.db.Spectateur;
import com.example.jo.repositories.SpectateurRepository;
import org.springframework.stereotype.Service;

@Service
public class SpectateurService {
    private final SpectateurRepository spectateurRepository;
    private final UserService userService;

    public SpectateurService(SpectateurRepository spectateurRepository, UserService userService) {
        this.spectateurRepository = spectateurRepository;
        this.userService = userService;
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

    public void deleteSpectateur(Spectateur spectateur) {
        if(!checkSpectateur(spectateur)){
            throw new IllegalStateException("Spectateur does not exist");
        }
        else spectateurRepository.deleteById(spectateur.getId());
    }

    public Spectateur connectSpectateur(Spectateur spectateur) {
        if (userService.connectUser(spectateur)) {
            return spectateurRepository.findById(spectateur.getId()).get();
        }
        else throw new IllegalStateException("Username/Email or password is incorrect");
    }
}
