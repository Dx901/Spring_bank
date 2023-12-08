package com.dyes.bank.models;

import com.dyes.bank.constants.TransactionType;
import jakarta.persistence.*;
import  com.dyes.bank.constants.AccountType;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public BigDecimal balance;

    @Id
    @Column(name = "account_number")
    public Long accountNumber;

    public Boolean isActive;

    public BigDecimal amount;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Transaction> transactions;

    public Account() {}

    public Account(User user, BigDecimal balance) {
        this.user = user;
        this.balance = balance;
        this.accountNumber = generateAccountNumber();

    }

    public Long generateAccountNumber() {

        Random random = new Random();
        return random.nextLong(10000);

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }


}
