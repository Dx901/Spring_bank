package com.dyes.bank.service;

import com.dyes.bank.exceptions.UserNotFoundException;
import com.dyes.bank.models.User;
import com.dyes.bank.repository.UserRepository;
import com.dyes.bank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    public UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        //Arrange
        when(userRepository.findAll()).thenReturn(Arrays.asList(
              new User(),
                new User()
        ));

        //Act
        List<User> users = userService.getAllUsers();

        //Assert
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void getUserById_shouldReturnUser() {
        long userId = 1L;
        User user = new User("Lebron Mikes");
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(userId);

        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getUserId());
    }

    @Test
    void getUserById_shouldThrowUserNotFoundException() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void createUser_shouldReturnCreateUser() {
        User newUser = new User("Dyes Jr");

        when(userRepository.save(newUser)).thenReturn(newUser);

        User createdUser = userService.createUser(newUser);

        assertNotNull(createdUser);
        assertEquals(newUser.getName(), createdUser.getName());
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() {
        long userId = 1L;
        User existingUser = new User("Dyes Jr");
        existingUser.setUserId(userId);

        User updatedUserData = new User("Updated Dyes Jr");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User updatedUser = userService.updateUser(userId, updatedUserData);

        assertNotNull(updatedUser);
        assertEquals(updatedUser.getName(), updatedUser.getName());
    }

    @Test
    void updateuser_shouldReturnNullForNonExistingUser() {
        long userId = 1L;
        User updatedUserData = new User("Updated Dyes Jr");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User updatedUser = userService.updateUser(userId, updatedUserData);

        assertNull(updatedUser);
    }

    @Test
    void deleteUser_shouldInvokeRepositoryDelete() {
        long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
