package com.dyes.bank.models;

import com.dyes.bank.constants.TransactionType;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account account;

    @Enumerated(EnumType.STRING)
    public TransactionType transactionType;

    public BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    public Date timestamp;

    @Column(unique = true)
    public String transactionNumber;

    // Constructors, getters, and setters

    public Transaction() {}

    public Transaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
        this.transactionNumber = UUID.randomUUID().toString();
        this.timestamp = new Date();
    }

    public Long generateTransactionNumber() {
        Random random = new Random();
        return random.nextLong();
    }

    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmounnt(){
        return amount;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}