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

    @Id
    @Column(name = "account_id")
    public Long accountId;

    public BigDecimal balance;

    public Long accountNumber;

    public Boolean isActive;

    public BigDecimal amount;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Transaction> transactions;

    public Account() {
    }

    public Account(User user, BigDecimal balance, Long accountNumber) {
        this.user = user;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountNumber = generateAccountNumber();

    }

    public Long generateAccountNumber() {

        Random random = new Random();
        int randomSuffix = random.nextInt(10000);

        String accountNumberString = user.getUserId() + "" + "" + randomSuffix;

        // Convert the concatenated string to an integer
        return Long.valueOf(accountNumberString);
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
