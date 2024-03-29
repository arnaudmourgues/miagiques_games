package com.example.jo.controllers;

import com.example.jo.entities.DTOs.JwtDto;
import com.example.jo.entities.DTOs.SignInDto;
import com.example.jo.entities.DTOs.SignUpUserDto;
import com.example.jo.config.auth.TokenProvider;
import com.example.jo.entities.User;
import com.example.jo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;
    @Autowired
    private TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpUserDto data) {
        service.signUpUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authUser);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }

    @DeleteMapping("/account/deleteUser")
    public ResponseEntity<?> delete() {
        service.delete();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/account")
    public ResponseEntity<String> getAccount() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
