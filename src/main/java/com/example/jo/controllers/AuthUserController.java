package com.example.jo.controllers;

import com.example.jo.entities.DTOs.*;
import com.example.jo.services.UserService;
import com.example.jo.services.UserSubService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthUserController {
    private UserService service;
    private UserSubService subService;

    @PostMapping("/signup")
    public ResponseEntity<JwtDto> signUp(@RequestBody SignUpUserDto data) {
        return ResponseEntity.ok(subService.signUpSpectateur(data));
    }

    @PostMapping("/signup/admin")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUpAdmin(@RequestBody SignUpDto data) {
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
        return ResponseEntity.ok(subService.signIn(data));
    }

    @GetMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> signOut() {
        service.signOut();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete() {
        //get the user sent in the header
        service.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/admin/{userId}")
    @PreAuthorize("hasRole('ROLE_ORGANISATEUR')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> deleteByOrganisateur(@PathVariable String userId) {
        service.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
