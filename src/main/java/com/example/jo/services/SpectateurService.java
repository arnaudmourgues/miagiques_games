package com.example.jo.services;

import com.example.jo.db.Spectateur;
import com.example.jo.db.User;
import com.example.jo.repositories.UserRespository;
import org.springframework.stereotype.Service;

@Service
public class SpectateurService {
    private final UserRespository userRepository;
    private final UserService userService;

    public SpectateurService(UserRespository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    
    public void createSpectateur(Spectateur spectateur){
        userService.create(spectateur);
    }

    private boolean checkSpectateur(Spectateur spectateur) {
        return userService.check(spectateur);
    }

    public void deleteSpectateur(Spectateur spectateur) {
        userService.delete(spectateur);
    }

    public Spectateur connectSpectateur(Spectateur spectateur) {
        User user = userService.connect(spectateur);
        if (user instanceof Spectateur) {
            return (Spectateur) user;
        } else throw new IllegalStateException("User is not a Spectateur");
    }
}
