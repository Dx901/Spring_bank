package com.dyes.bank.controller;

import com.dyes.bank.repository.UserRepository;
import com.dyes.bank.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }
}
