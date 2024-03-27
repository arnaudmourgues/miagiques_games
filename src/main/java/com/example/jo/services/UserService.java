package com.example.jo.services;

import com.example.jo.db.entities.User;
import com.example.jo.repositories.UserRespository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRespository userRespository;
    private final SpectateurService spectateurService;

    public UserService(UserRespository userRespository, SpectateurService spectateurService) {
        this.userRespository = userRespository;
        this.spectateurService = spectateurService;
    }

    public void createUser(User user){
        //check if user already exists
        if(checkUser(user)){
            throw new IllegalStateException("User already exists");
        }
        else userRespository.save(user);

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
}
