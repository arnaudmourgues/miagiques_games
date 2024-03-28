package com.example.jo.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.jo.db.entities.User;
import com.example.jo.repositories.UserRespository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {
    private final UserRespository userRespository;

    public UserService(UserRespository userRespository, SpectateurService spectateurService) {
        this.userRespository = userRespository;
    }

    public void createUser(User user){
        //check if user already exists
        if(checkUser(user)){
            throw new IllegalStateException("User already exists");
        }
        else{
            //hash password
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);
            user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
            userRespository.save(user);
        }

    }

    public boolean checkUser(User user) {
        return userRespository.existsById(user.getId());
    }

    public void deleteUser(User user) {
        if(!checkUser(user)){
            throw new IllegalStateException("User does not exist");
        }
        else userRespository.deleteById(user.getId());
    }

    public boolean connectUser(User user) {
        if(!checkUser(user)){
            throw new IllegalStateException("User does not exist");
        }
        else{
            User user1 = userRespository.findById(user.getId()).get();
            return BCrypt.verifyer().verify(user.getPassword().toCharArray(), user1.getPassword()).verified;
        }
    }
}
