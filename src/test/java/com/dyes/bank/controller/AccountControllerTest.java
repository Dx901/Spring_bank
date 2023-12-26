package com.dyes.bank.controller;

import com.dyes.bank.exceptions.AccountNotFoundException;
import com.dyes.bank.models.Account;
import com.dyes.bank.models.User;
import com.dyes.bank.services.AccountService;
import com.dyes.bank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    public AccountService accountService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllAccounts_shouldReturnListOfAccounts() {
        //Arrange
        List<Account> accountList = Arrays.asList(new Account(), new Account());
        when(accountService.getAllAccounts()).thenReturn(accountList);

        //act
        List<Account> result = accountController.getAllAccounts();

        //assrt
        assertEquals(accountList, result);
    }

    @Test
    public void getAccountById_shouldReturnAccount() {
        //arrange
        Long accountId = 1L;
        Account account = new Account();
        when(accountService.getAccountById(accountId)).thenReturn(account);

        //Act
        ResponseEntity<Account> responseEntity = accountController.getAccountById(accountId);

        //assert
        assertEquals(account, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountById_shouldReturnNotFound_whenAccountNotFoundExceptionThrown() {

        //arrange
        Long accountId = 1L;
        when(accountService.getAccountById(accountId)).thenThrow(new AccountNotFoundException("Account not found"));

        //act
        ResponseEntity<Account> responseEntity = accountController.getAccountById(accountId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void createAccount_shouldReturnCreatedAccount() {
        //arrange
        ObjectReceiver request = new ObjectReceiver();
        request.setUSerId(1L);
        request.setBalance(BigDecimal.TEN);

        User user = new User();
        Account createdAccount = new Account();
        when(userService.getUserById(request.getUserId())).thenReturn(user);
        when(accountService.createAccount(user, request.getBalance())).thenReturn(createdAccount);

        //Act
        ResponseEntity<Account> responseEntity = accountController.createAccount(request);

        //Assert
        assertEquals(createdAccount, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccount_shouldReturnOk() {
        //arrange
        Long accountId = 1L;
        Account updatedAccount = new Account();

        //Acct
        ResponseEntity<Account> responseEntity = accountController.updateAccount(accountId, updatedAccount);

        //assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccount_shouldReturnNotFound_whenAccountNotFoundExceptionThrown() {

        //arrange
        Long accountId = 1L;
        Account updatedAccount = new Account();
        doThrow(new AccountNotFoundException("Account Not Found")).when(accountService).updateAccount(accountId, updatedAccount);

        //Act
        ResponseEntity<Account> responseEntity = accountController.updateAccount(accountId,updatedAccount);

        //assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAccount_shouldReturnNoContent() {
        //arrange
        Long accountId = 1L;

        //act
        ResponseEntity<Void> responseEntity = accountController.deleteAccount(accountId);

        //assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAccount_shouldReturnNotFound_whenAccountNotFoundExceptionThrown() {
        //Arrange
        Long accountId = 1L;
        doThrow(new AccountNotFoundException("Account not found")).when(accountService).deleteAccount(accountId);

        //Act
        ResponseEntity<Void> responseEntity = accountController.deleteAccount(accountId);

        //assertt
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
