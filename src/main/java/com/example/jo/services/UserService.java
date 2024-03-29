package com.example.jo.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.jo.entities.User;
import com.example.jo.repositories.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {
    private final UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public void create(User user) {
        //hash password
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
        userRespository.save(user);
    }

    public boolean check(User user) {
        return userRespository.existsById(user.getId());
    }

    public void delete(User user) {
        if (!check(user)) {
            throw new IllegalStateException("User does not exist");
        } else userRespository.deleteById(user.getId());
    }

    public boolean checkByEmail(User user) {
        return userRespository.existsByEmail(user.getEmail());
    }
}
