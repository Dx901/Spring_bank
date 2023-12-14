package com.dyes.bank.controller;

import java.math.BigDecimal;


public class ObjectReceiver {

    public ObjectReceiver() {}

    public BigDecimal amount;

    public Long transactionId;
    public Long userId;
    public BigDecimal balance;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}