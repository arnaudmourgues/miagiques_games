package com.example.jo.services;

import com.example.jo.entities.Billet;
import com.example.jo.entities.DTOs.BilletDto;
import com.example.jo.entities.Epreuve;
import com.example.jo.entities.enums.Etat;
import com.example.jo.errors.exceptions.ForfeitException;
import com.example.jo.repositories.BilletRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BilletService {
    private final BilletRepository billetRepository;
    private final EpreuveService epreuveService;
    private final UserService userService;


    public void createBillet(@NotNull BilletDto data) {
        Epreuve epreuve = epreuveService.getEpreuveById(data.epreuveId());
        if (billetRepository.countByEpreuve(epreuve) >= epreuve.getNbPlacesSpectateurs()) {
            throw new IllegalArgumentException("Plus de places disponibles");
        }
        if (billetRepository.findBySpectateurAndEpreuve(userService.getAuthenticatedUser(), epreuve).size() + data.nbBillets() > 4) {
            throw new IllegalArgumentException("Vous dépassez le nombre de billets maximum par personne.");
        }
        for (int i = 0; i < data.nbBillets(); i++) {
            Billet billet = new Billet();
            billet.setEpreuve(epreuve);
            billet.setEtat(Etat.VALIDE);
            billet.setSpectateur(userService.getAuthenticatedUser());
            billet.setPrix(data.prix());
            billetRepository.save(billet);
        }
    }

    public double cancelBillet(@NotNull UUID epreuveId) {
        List<Billet> billets = billetRepository.findBySpectateurAndEpreuve(userService.getAuthenticatedUser(), epreuveService.getEpreuveById(epreuveId));
        if (billets.isEmpty()) {
            throw new IllegalArgumentException("Vous n'avez pas de billet pour cette épreuve.");
        }
        double resteARembourser = 0;
        for (Billet billet : billets) {
            if (billet.getEpreuve().getDate().isBefore(Instant.now().plusSeconds(3 * 24 * 60 * 60))) {
                throw new ForfeitException("L'épreuve est dans moins de 3 jours, vous ne pouvez plus annuler votre billet.");
            }
            if (billet.getEtat() == Etat.VALIDE) {
                if (billet.getEpreuve().getDate().isBefore(Instant.now().plusSeconds(7 * 24 * 60 * 60))) {
                    resteARembourser += billet.getPrix() * 0.5;
                } else {
                    resteARembourser += billet.getPrix();
                }
                billet.setEtat(Etat.ANNULE);
                billetRepository.save(billet);
            }
        }
        return resteARembourser;
    }

    public void controlBillet(UUID billetId) {
        Billet billet = billetRepository.findById(billetId).orElseThrow(() -> new IllegalArgumentException("Le billet n'existe pas."));
        if (billet.getEtat() != Etat.VALIDE) {
            throw new IllegalArgumentException("Le billet a déjà été utilisé ou annulé.");
        }
        billet.setEtat(Etat.DEJA_UTILISE);
        billetRepository.save(billet);
    }

    public Iterable<Billet> getBilletsBySpectateur() {
        return billetRepository.findBySpectateur(userService.getAuthenticatedUser());
    }

    public double sellOneBillet(UUID uuid) {
        Billet billet = billetRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Le billet n'existe pas."));
        if (billet.getEtat() != Etat.VALIDE) {
            throw new IllegalArgumentException("Le billet a déjà été utilisé ou annulé.");
        }
        billet.setEtat(Etat.ANNULE);
        billetRepository.save(billet);
        if (billet.getEpreuve().getDate().isBefore(Instant.now().plusSeconds(7 * 24 * 60 * 60))) {
            return billet.getPrix() * 0.5;
        }
        return billet.getPrix();
    }

    public Iterable<Billet> findAll() {
        return billetRepository.findAll();
    }
}
