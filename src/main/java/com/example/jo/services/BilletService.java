package com.example.jo.services;

import com.example.jo.entities.Epreuve;
import com.example.jo.repositories.BilletRepository;
import org.springframework.stereotype.Service;

@Service
public class BilletService {
    private final BilletRepository billetRepository;

    public BilletService(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    public void createBillet(Epreuve epreuve) {

    }
}
