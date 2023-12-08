package com.dyes.bank.controller;

import java.math.BigDecimal;


public class ObjectReceiver {

    public ObjectReceiver() {}

    public Long userId;
    public BigDecimal balance;

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

}
