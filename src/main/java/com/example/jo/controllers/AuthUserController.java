package com.example.jo.controllers;

import com.example.jo.config.auth.TokenProvider;
import com.example.jo.entities.DTOs.*;
import com.example.jo.entities.User;
import com.example.jo.services.AuthUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthUserController {
    private AuthenticationManager authenticationManager;
    private AuthUserService service;
    private TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpUserDto data) {
        service.signUpSpectateur(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signup/admin")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUpAdmin(@RequestBody SignUpDto data) {
        System.out.println(data);
        service.signUpAdmin(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signup/participant")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUpParticipant(@RequestBody SignUpParcipantDto data) {
        service.signUpParticipant(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JwtDto> signIn(@RequestBody SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }

    @DeleteMapping("/account/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete() {
        service.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/delete/organisateur")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> deleteByOrganisateur(@RequestBody UUID userId) {
        service.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(user);
    }
}
