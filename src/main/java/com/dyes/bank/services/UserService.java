package com.dyes.bank.services;

import com.dyes.bank.exceptions.UserNotFoundException;
import com.dyes.bank.models.User;
import com.dyes.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {



    @Autowired
    public UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        //Firts check if the user exists
        Optional<User> existingUserOtional = userRepository.findById(userId);
        if (existingUserOtional.isPresent()) {
            User existingUser = existingUserOtional.get();

            //Update now with the new data
            existingUser.setName(user.getName());


            //save the update
            return userRepository.save(existingUser);
        } else {
            return null;
        }

    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
