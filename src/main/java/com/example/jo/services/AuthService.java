package com.example.jo.services;

import com.example.jo.entities.*;
import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
import com.example.jo.entities.enums.UserRole;
import com.example.jo.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRespository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsernameOrEmail(username);
    }

    public void signUpSpectateur(SignUpUserDto data) {
        if (repository.findByUsernameOrEmail(data.login()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Spectateur newUser = new Spectateur(data.login(), encryptedPassword, UserRole.SPECTATEUR);
        repository.save(newUser);
    }

    public void signUpByOrganisateur(SignUpDto data) {
        if (repository.findByUsernameOrEmail(data.login()) != null){
            throw new IllegalArgumentException("User already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser;
        switch (data.role()) {
            case ORGANISATEUR -> newUser = new Organisateur(data.login(), encryptedPassword, UserRole.ORGANISATEUR);
            case CONTROLEUR -> newUser = new Controleur(data.login(), encryptedPassword, UserRole.CONTROLEUR);
            case PARTICIPANT -> newUser = new Participant(data.login(), encryptedPassword, UserRole.PARTICIPANT);
            default -> throw new IllegalArgumentException("Invalid role");
        }
        repository.save(newUser);
    }

    public void deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = loadUserByUsername(auth.getName());
        repository.delete((User) user);
    }
}
