package com.dyes.bank.models;

import com.dyes.bank.constants.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account account;

    @Enumerated(EnumType.STRING)
    public TransactionType transactionType;

    public BigDecimal amount;

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
}