package com.dyes.bank.controller;

import com.dyes.bank.exceptions.UserNotFoundException;
import com.dyes.bank.models.User;
import  com.dyes.bank.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

        @Autowired
        public UserService userService;

        @GetMapping
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }

        @GetMapping("/{userId}")
        public ResponseEntity<User> getUserById(@PathVariable Long userId) {
            try {
                User user = userService.getUserById(userId);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (UserNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
//        public User createUser(@RequestBody User user) {
//            return userService.createUser(user);
//        }

        public ResponseEntity<User> createUser(@RequestBody User user) {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }


        @PutMapping("/{userId}")
        public User updateUser(@PathVariable Long userId, @RequestBody User user) {
            return userService.updateUser(userId, user);
        }

        @DeleteMapping("/{userId}")
        public void deleteUser(@PathVariable Long userId) {
            userService.deleteUser(userId);
        }
}
