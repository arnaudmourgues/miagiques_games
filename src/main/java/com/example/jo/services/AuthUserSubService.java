package com.example.jo.services;

import com.example.jo.config.auth.TokenProvider;
import com.example.jo.entities.DTOs.JwtDto;
import com.example.jo.entities.DTOs.SignInDto;
import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
import com.example.jo.entities.Spectateur;
import com.example.jo.entities.User;
import com.example.jo.entities.enums.UserRole;
import com.example.jo.repositories.UserRespository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUserSubService {
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;
    UserRespository repository;

    public @NotNull JwtDto generateJwtToken(SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return new JwtDto(accessToken);
    }

    public JwtDto signIn(SignInDto data) {
        if(repository.findByEmail(data.login()) == null) {
            throw new IllegalArgumentException("L'utilisateur n'existe pas");
        }
        return generateJwtToken(data);
    }

    public JwtDto signUpSpectateur(SignUpUserDto data) {
        if(repository.findByEmail(data.email()) != null) {
            throw new IllegalArgumentException("Le nom d'utilisateur est déjà pris");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Spectateur newUser = new Spectateur(data.email(), encryptedPassword, data.nom(), data.prenom(), UserRole.SPECTATEUR);
        repository.save(newUser);
        return generateJwtToken(new SignInDto(data.email(), data.password()));
    }
}
