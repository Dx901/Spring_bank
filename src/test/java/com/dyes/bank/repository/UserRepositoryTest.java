package com.dyes.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.dyes.bank.models.User;
import com.dyes.bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_ShouldReturnSavedUser() {
        // Given
        User user = new User();
        user.setName("John Doe");

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertNotNull(savedUser.getUserId());
        assertEquals("John Doe", savedUser.getName());

    }

    @Test
    public void getUsers_ShouldReturnAllUsers() {
        User user1 = new User();
        user1.setName("John Pombe");

        User user2 = new User();
        user2.setName("JAne Doe");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> allUsers = userRepository.findAll();

        //then
        assertEquals(2, allUsers.size());
    }

    @Test
    public void getUSersById_shouldReturnUser() {

        User user = new User();
        user.setName("John Pombe");

        User savedUser = userRepository.save(user);

        //When
        Optional<User> retreivedUserOptional = userRepository.findById(savedUser.getUserId());

        //Then
        assertTrue(retreivedUserOptional.isPresent());
        User retreivedUser = retreivedUserOptional.get();
        assertEquals(savedUser.getUserId(), retreivedUser.getUserId());
        assertEquals(savedUser.getName(), retreivedUser.getName());
    }

    @Test
    public void deleteUser_ShouldDeleteUser() {
        //Given
        User user = new User();
        user.setName("Sawka Dyes");
        User savedUser = userRepository.save(user);

        //When
        userRepository.deleteById(savedUser.getUserId());

        //Then
        assertFalse(userRepository.existsById(savedUser.getUserId()));
    }
}
