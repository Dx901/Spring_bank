package com.dyes.bank.services;

import com.dyes.bank.constants.TransactionType;
import com.dyes.bank.exceptions.AccountNotFoundException;
import com.dyes.bank.exceptions.TransactionNotFoundException;
import com.dyes.bank.models.Account;
import com.dyes.bank.models.Transaction;
import com.dyes.bank.repository.AccountRepository;
import com.dyes.bank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction createTransaction(Long accountId, TransactionType transactionType, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("No account with ID: " + accountId));

        if (TransactionType.WITHDRAWAL.equals(transactionType) && amount.compareTo(account.getBalance()) > 0) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }

        Transaction transaction = new Transaction(account, transactionType, amount);
        transactionRepository.save(transaction);

        updateAccountBalance(account, transactionType, amount);
        return transaction;
    }

    public void updateAccountBalance(Account account, TransactionType transactionType, BigDecimal amount) {
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance;

        if (TransactionType.DEPOSIT.equals(transactionType)) {
            newBalance = currentBalance.add(amount);
        } else {
            newBalance = currentBalance.subtract(amount);
        }

        account.setBalance(newBalance);

        if (newBalance.compareTo(BigDecimal.valueOf(500)) >= 0) {
            account.setIsActive(true);
        }
        accountRepository.save(account);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction(account, amount);
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long transactionId) throws InstantiationException, IllegalAccessException {
        return transactionRepository.findById(transactionId.getClass().newInstance())
                .orElseThrow(() -> new TransactionNotFoundException("No transaction with that ID"));
    }



    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}