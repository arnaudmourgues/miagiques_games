package com.example.jo.services;

import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
import com.example.jo.entities.Controleur;
import com.example.jo.entities.Organisateur;
import com.example.jo.entities.Spectateur;
import com.example.jo.entities.User;
import com.example.jo.entities.enums.UserRole;
import com.example.jo.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRespository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsernameOrEmail(username);
    }

    public UserDetails signUpUser(SignUpUserDto data) {
        if (repository.findByUsernameOrEmail(data.login()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Spectateur newUser = new Spectateur(data.login(), encryptedPassword, UserRole.USER);
        return repository.save(newUser);
    }

    public UserDetails signUp(SignUpDto data) {
        if (repository.findByUsernameOrEmail(data.login()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser;
        switch (data.role()) {
            case ORGANISATEUR -> newUser = new Organisateur(data.login(), encryptedPassword, UserRole.ORGANISATEUR);
            case CONTROLLEUR -> newUser = new Controleur(data.login(), encryptedPassword, UserRole.CONTROLLEUR);
            default -> throw new IllegalArgumentException("Invalid role");
        }
        return repository.save(newUser);
    }

    public void delete() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = auth.getPrincipal();
        System.out.println(user);
    }

    public String getAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (String) auth.getPrincipal();
    }
}
