package com.dyes.bank.models;

import com.dyes.bank.constants.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    public Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    public Account account;

    @Enumerated(EnumType.STRING)
    public TransactionType transactionType;

    public BigDecimal amount;

    @CreationTimestamp
    public LocalDateTime timestamp;

    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;

    }

    public Transaction(Account account, TransactionType transactionType, BigDecimal amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public long getTransactionId() {
        return transactionId;
    }
}