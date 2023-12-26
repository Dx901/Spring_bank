package com.dyes.bank.controller;

import com.dyes.bank.constants.TransactionType;
import com.dyes.bank.models.Transaction;
import com.dyes.bank.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    @Mock
    public TransactionService transactionService;

    @InjectMocks
    public TransactionController transactionController;

    public TransactionControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTransactions_shouldReturnListOfTransactions() {
        //Arrange
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        //Act
        List<Transaction> result = transactionController.getAllTransactions();

        //Assert
        assertEquals(transactions, result);
    }

    @Test
    public void createTransaction_shouldReturnCreatedTransaction() {
        //arrange
        TransactionService transactionService = mock((TransactionService.class));
        TransactionController.TransactionRequest request = new TransactionController.TransactionRequest();
        request.setAccountId(1L);
        request.setTransactionType(TransactionType.DEPOSIT);
        request.setAmount(BigDecimal.TEN);

        Transaction createdTransaction = new Transaction();
        when(transactionService.createTransaction(anyLong(), any(), any())).thenReturn(createdTransaction);

        TransactionController transactionController = new TransactionController(transactionService);

        //Act
        ResponseEntity<Transaction> responseEntity = transactionController.createTransaction(request);

        //Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdTransaction, responseEntity.getBody());
    }

    @Test
    void getTransactionById_shouldReturnTransaction() {
        //Arrange
        TransactionService transactionService = mock(TransactionService.class);
        Transaction transaction = new Transaction();
        when(transactionService.getTransactionById(anyLong())).thenReturn(transaction);

        TransactionController transactionController = new TransactionController(transactionService);

        //Act
        ResponseEntity<Transaction> responseEntity = transactionController.getTransactionById(1L);

        //Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
    }
}
