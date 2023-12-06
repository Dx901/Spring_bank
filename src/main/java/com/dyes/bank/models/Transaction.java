package com.dyes.bank.models;
import  com.dyes.bank.constants.TransactionType;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    public Account account;

    public @Id int transactionId;

    @Enumerated(EnumType.STRING)
    public TransactionType transactionType;
    public LocalDateTime timestamp;
    public double amount;

    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(Account account, TransactionType tranasctionType, double amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
