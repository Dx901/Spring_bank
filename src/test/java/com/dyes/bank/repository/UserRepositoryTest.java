package com.dyes.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.dyes.bank.models.User;
import com.dyes.bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}
