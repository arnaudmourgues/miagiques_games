package com.example.jo.services;

import com.example.jo.entities.Controleur;
import com.example.jo.repositories.UserRespository;
import org.springframework.stereotype.Service;

@Service
public class ControleurService {
    private final UserRespository userRepository;

    public ControleurService(UserRespository userRepository) {
        this.userRepository = userRepository;
    }
}
