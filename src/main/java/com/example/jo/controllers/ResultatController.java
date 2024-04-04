package com.example.jo.controllers;

import com.example.jo.entities.DTOs.ResultatDto;
import com.example.jo.services.ResultatService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ResultatController {
    private final ResultatService resultatService;

    @PostMapping("/admin/publiateResultat")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void publiateResultat(ResultatDto data) {
        resultatService.publiateResultat(data);
    }

    @PostMapping("/admin/publiateMultipleResultat")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    public void publiateMultipleResultat(List<ResultatDto> data) {
        for (ResultatDto resultatDto : data) {
            resultatService.publiateResultat(resultatDto);
        }
    }
}
