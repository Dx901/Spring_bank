package com.dyes.bank.service;

import com.dyes.bank.exceptions.AccountNotFoundException;
import com.dyes.bank.models.User;
import com.dyes.bank.models.Account;
import com.dyes.bank.repository.AccountRepository;
import com.dyes.bank.services.AccountService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAccount_shouldReturnCreatedAccount() {
        //Arange
        User user = new User("John Does");
        BigDecimal initialBalance = BigDecimal.valueOf(1000.00);

        //Mock repository behavior
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(new Account(user, initialBalance));

        //Act
        Account createdAccount = accountService.createAccount(user, initialBalance);

        //Assert
        assertEquals(user, createdAccount.getUser());
        assertEquals(initialBalance, createdAccount.getBalance());
    }

    @Test
    public void getAccountById_shouldReturnAccount() {
        //Arrange
        Long accountId = 1L;
        User user = new User("Jane Doe");
        BigDecimal initialBalance = BigDecimal.valueOf(1000.00);
        Account mockAccount = new Account(user, initialBalance);

        //Mocking repo behaviour
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(mockAccount));

        //Act
        Account retrievedAccount = accountService.getAccountById(accountId);

        //Assert
        assertEquals(mockAccount, retrievedAccount);
    }

    @Test
    public void getAccountById_shouldThrowAccountNotFoundException() {
        //Arrange
        Long accountId = 1L;

        //Mocking repo behaviour
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        //Act and assert
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(accountId));
    }

    @Test
    public void updateAccount_shouldUpdateAndReturnUpdatedAccount() {
        //Arrange
        Long accountId = 1L;
        BigDecimal newBalance = BigDecimal.valueOf(1500.0);
        Account existingAccount = new Account(new User("Jane Doe"), BigDecimal.valueOf(1000.0));

        //Repo vbehavior
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(existingAccount);

        //Act
        accountService.updateAccount(accountId, new Account(null, newBalance));

        //Assert
        assertEquals(newBalance, existingAccount.getBalance());
    }

    @Test
    public void deleteAccount_shouldDeleteAccount() {
        //Arrange
        Long accountId = 1L;
        Account existingAccount = new Account(new User("John Doe"), BigDecimal.valueOf(1000.00));

        //Mocking repository behaviour
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));

        //Act
        accountService.deleteAccount(accountId);

        //VErify the amethod was called with correct account
        //Assert
        verify(accountRepository, times(1)).delete(existingAccount);
    }

    @Test
    public void deleteAccount_shouldThrowAccountNotFoundException() {
        //Arrrange
        Long accountId = 1L;

        //Mocking behavoir of repository
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        //Act and assert
        assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccount(accountId));
    }
}
