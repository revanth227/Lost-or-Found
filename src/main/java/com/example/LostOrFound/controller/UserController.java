package com.example.LostOrFound.controller;

import com.example.LostOrFound.dataEntity.Users;
import com.example.LostOrFound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Users registerNewUser(@RequestBody Users users){
        return userService.saveNewUser(users);
    }

}
