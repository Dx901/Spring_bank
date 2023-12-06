package com.dyes.bank.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    public User user;
    public @Id Long accountId;

    public Enum accountType;
    public double balance;
    public int accountNumber;
    public boolean isActive;
//    public List<Transaction> transactions;

    public Account() {}

    public Account(User user, Enum accountType, double balance, int accountNumber) {
        this.user = user;
        this. accountType= accountType;
        this.balance = balance;
        this.accountNumber = accountNumber;
//        this.accountNumber = generateAccountNumber();

    }

    public Long getAccountId() {
        return accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Enum getAccountType() {
        return accountType;
    }

    public void setAccountType(Enum accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

}
