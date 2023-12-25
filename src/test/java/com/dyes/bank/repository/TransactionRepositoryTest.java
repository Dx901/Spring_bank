package com.dyes.bank.repository;

import com.dyes.bank.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void getAllTransactions_shouldReturnAllTransactions() {
        //Arrange
        //Act
        List<Transaction> transactions = transactionRepository.findAll();

        //Assert
        assertNotNull(transactions);
    }

    @Test
    public void getTransactionById_shouldReturnTransaction() {
        //Arrange
        Transaction savedTransaction = transactionRepository.save(new Transaction());

        //Act
        Transaction retrivedTransaction = transactionRepository.findById(savedTransaction.getTransactionId()).orElse(null);

        //Assert
        assertNotNull(retrivedTransaction);
        assertEquals(savedTransaction.getTransactionId(), retrivedTransaction.getTransactionId());

    }
}
