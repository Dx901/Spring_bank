package com.dyes.bank.controller;


import com.dyes.bank.constants.TransactionType;
import com.dyes.bank.exceptions.TransactionNotFoundException;
import com.dyes.bank.models.Transaction;
import com.dyes.bank.services.AccountService;
import com.dyes.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions", produces = "application/json")
public class TransactionController {

    @Autowired
    public TransactionService transactionService;
    public AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService){
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {return transactionService.getAllTransactions();}

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
