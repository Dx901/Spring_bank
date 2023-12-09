package com.dyes.bank.services;

import com.dyes.bank.constants.TransactionType;
import com.dyes.bank.exceptions.TransactionNotFoundException;
import com.dyes.bank.models.Account;
import com.dyes.bank.models.Transaction;
import com.dyes.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }



    public Transaction createTransaction(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction(account, amount);
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId.getClass())
                .orElseThrow(() -> new TransactionNotFoundException("No transaction with that ID"));
    }


}
