package com.example.jo.controllers;

import com.example.jo.config.auth.TokenProvider;
import com.example.jo.entities.DTOs.JwtDto;
import com.example.jo.entities.DTOs.SignInDto;
import com.example.jo.entities.DTOs.SignUpDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
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

    @PostMapping("/signup/organisateur")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUpByOrganisateur(@RequestBody SignUpDto data) {
        System.out.println(data);
        service.signUpByOrganisateur(data);
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteByOrganisateur() {
        service.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount() {
        //get role of the user
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        var user = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user);
        return ResponseEntity.ok(user);
    }
}
