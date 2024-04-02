package com.example.jo.services;

import com.example.jo.entities.User;
import com.example.jo.repositories.UserRespository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public boolean check(User user) {
        return userRespository.existsById(user.getId());
    }

    public void delete(User user) {
        if (!check(user)) {
            throw new IllegalStateException("User does not exist");
        } else userRespository.delete(user);
    }

    public User findByUsername(String username) {
        return userRespository.findByUsername(username);
    }
}
