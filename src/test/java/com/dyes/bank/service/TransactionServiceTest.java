package com.dyes.bank.service;

import com.dyes.bank.constants.TransactionType;
import com.dyes.bank.exceptions.AccountNotFoundException;
import com.dyes.bank.exceptions.TransactionNotFoundException;
import com.dyes.bank.models.Transaction;
import com.dyes.bank.repository.AccountRepository;
import com.dyes.bank.repository.TransactionRepository;
import com.dyes.bank.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    public TransactionRepository transactionRepository;

    @Mock
    public AccountRepository accountRepository;

    @InjectMocks
    public TransactionService transactionService;

    @Test
    public void getAllTransactions_shouldReturnAllTransactions() {
        //Arrange
        List<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.findAll()).thenReturn(transactions);

        //Act
        List<Transaction> result = transactionService.getAllTransactions();

        //Assert
        assertEquals(transactions, result);
    }

    @Test
    void getTransactionById_shouldReturnTransaction() {
        // Arrange
        Long nonExistingTransactionId = 999L;

        // Act and Assert
        TransactionNotFoundException exception = assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.getTransactionById(nonExistingTransactionId);
        });

        assertEquals("No transaction with that ID", exception.getMessage());
    }


    @Test
    public void getTransactionById_ShouldThrowTransactionNotFoundException() {
        //Arrange
        Long transactionId = 1L;
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        //Act and assert
        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionById(transactionId));
    }

    @Test
    public void createTransaction_shouldThrowAccountNotFoundException() {
        // Arrange
        Long accountId = 1L;
        TransactionType transactionType = TransactionType.DEPOSIT;
        BigDecimal amount = BigDecimal.TEN;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AccountNotFoundException.class, () -> transactionService.createTransaction(accountId, transactionType, amount));
    }
}
