package com.example.jo.controllers;

import com.example.jo.entities.Epreuve;
import com.example.jo.services.EpreuveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class EpreuveController {
    private final EpreuveService epreuveService;

    public EpreuveController(EpreuveService epreuveService) {
        this.epreuveService = epreuveService;
    }

    @PostMapping("/createEpreuve")
    public void createEpreuve(@RequestBody Epreuve epreuve) {
        epreuveService.createEpreuve(epreuve);
    }

    @DeleteMapping("/deleteEpreuve")
    public void deleteEpreuve(@RequestBody Epreuve epreuve) {
        epreuveService.deleteEpreuve(epreuve);
    }
}
