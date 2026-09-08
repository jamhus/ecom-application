package com.app.ecomapplication;

import Entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    final List<User> users = new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> GetAllUsers() {
        return users;
    }

    @PostMapping("/api/users")
    public List<User> CreateUser(@RequestBody User user) {
        users.add(user);
        return users;
    }
}
