package com.example.jo.controllers;

import com.example.jo.entities.DTOs.InfrastructureSportiveDto;
import com.example.jo.entities.InfrastructureSportive;
import com.example.jo.services.InfrastructureSportiveService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
public class InfrastructureSportiveController {
    private final InfrastructureSportiveService infrastructureSportiveService;

    @PostMapping("/admin/infrastructureSportive")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void createInfrastructureSportive(@RequestBody InfrastructureSportiveDto data) {
        System.out.println("data" + data);
        infrastructureSportiveService.createInfrastructureSportive(data);
    }

    @DeleteMapping("/admin/infrastructureSportive/{infrastructureSportiveId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void deleteInfrastructureSportive(@PathVariable String infrastructureSportiveId) {
        infrastructureSportiveService.deleteInfrastructureSportive(UUID.fromString(infrastructureSportiveId));
    }

    @GetMapping("/infrastructureSportive")
    public Iterable<InfrastructureSportive> getAllInfrastructureSportive() {
        return infrastructureSportiveService.getAllInfrastructureSportive();
    }
}
