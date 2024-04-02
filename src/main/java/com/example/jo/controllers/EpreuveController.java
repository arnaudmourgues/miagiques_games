package com.example.jo.controllers;

import com.example.jo.entities.DTOs.EpreuveDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.services.EpreuveService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class EpreuveController {
    private final EpreuveService epreuveService;
    private final ModelMapper modelMapper;

    @PostMapping("/createEpreuve")
    public void createEpreuve(@RequestBody EpreuveDto epreuve) {
        epreuveService.createEpreuve(modelMapper.map(epreuve, Epreuve.class));
    }

    @DeleteMapping("/deleteEpreuve")
    public void deleteEpreuve(@RequestBody EpreuveDto epreuve) {
        epreuveService.deleteEpreuve(modelMapper.map(epreuve, Epreuve.class));
    }
}
