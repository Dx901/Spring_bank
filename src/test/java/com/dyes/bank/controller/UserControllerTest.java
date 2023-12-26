package com.dyes.bank.controller;

import com.dyes.bank.exceptions.UserNotFoundException;
import com.dyes.bank.models.User;
import com.dyes.bank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    public UserService userService;

    @InjectMocks
    public UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsers_shouldReturnListOfUsers() {
        //Arange
        List<User> userList = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(userList);

        //Act
        List<User> result = userController.getAllUsers();

        //Assert
        assertEquals(userList, result);
    }

    @Test
    public void getUserById_shouldReturnUser() {
        //Arrange
        Long userId = 1L;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(user);

        //Act
        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        //Assert
        assertEquals(user, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getUserById_shouldReturnNotFound_WhenUserNotFoundExceptionThrown() {

        //arrange
        Long userId = 1L;
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));

        //act
        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        //assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void createUser_shouldReturnCreatedUser() {
        //arrange
        User inputUser = new User();
        User createdUser = new User();
        when(userService.createUser(inputUser)).thenReturn(createdUser);

        //act
        ResponseEntity<User> responseEntity = userController.createUser(inputUser);

        //Assert
        assertEquals(createdUser, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void updateUser_shouldReturnUpdatedUser() {
        //Arrange
        Long userId = 1L;
        User inputUser = new User();
        User updatedUser = new User();
        when(userService.updateUser(userId, inputUser)).thenReturn(updatedUser);

        //Act
        User result = userController.updateUser(userId, inputUser);

        //assert
        assertEquals(updatedUser, result);
    }

    @Test
    void deleteUser_shouldInvokeDeleteUserInService() {
        //arrange
        Long userId = 1L;

        //Act
        userController.deleteUser(userId);

        //Assert
        verify(userService, times(1)).deleteUser(userId);
    }
}
