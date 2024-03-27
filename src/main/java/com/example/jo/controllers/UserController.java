package com.example.jo.controllers;

import com.example.jo.db.entities.User;
import com.example.jo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PostMapping("/connectUser")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkUser(@RequestBody User user){
        return userService.checkUser(user);
    }

    @PostMapping("/deleteUser")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user){
        userService.deleteUser(user);
    }

}
