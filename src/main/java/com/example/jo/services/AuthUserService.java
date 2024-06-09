package com.example.jo.services;

import com.example.jo.entities.*;
import com.example.jo.entities.DTOs.*;
import com.example.jo.repositories.UserRespository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.jo.entities.enums.UserRole.*;

@Service
@AllArgsConstructor
public class AuthUserService implements UserDetailsService {
    UserRespository repository;
    DelegationService delegationService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username);
    }

    public void signUpAdmin(SignUpDto data) {
        if (repository.findByEmail(data.email()) != null){
            throw new IllegalArgumentException("User already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser;
        switch (data.role()) {
            case "ORGANISATEUR" -> newUser = new Organisateur(data.email(), encryptedPassword, ORGANISATEUR);
            case "CONTROLEUR" -> newUser = new Controleur(data.email(), encryptedPassword, CONTROLEUR);
            default -> throw new IllegalArgumentException("Rôle invalide.");
        }
        repository.save(newUser);
    }

    public void signUpParticipant(SignUpParcipantDto data) {
        if (repository.findByEmail(data.email()) != null) {
            throw new IllegalArgumentException("L'utilisateur existe déjà");
        }
        if(!delegationService.isDelegationExist(UUID.fromString(data.delegationid()))) {
            throw new IllegalArgumentException("La délégation n'existe pas");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Delegation delegation = delegationService.getDelegationById(UUID.fromString(data.delegationid()));
        Participant newUser = new Participant(data.email(), encryptedPassword, delegation, data.nom(), data.prenom());
        repository.save(newUser);
    }

    public void deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = loadUserByUsername(auth.getName());
        repository.delete((User) user);
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) repository.findByEmail(auth.getName());
    }

    public void deleteUserById(UUID userId) {
        repository.deleteById(userId);
    }

    public void signOut() {
        SecurityContextHolder.clearContext();
    }

    public Iterable<Participant> getParticipants() {
        return repository.findAllParticipants();
    }
}
