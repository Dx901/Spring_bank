package com.dyes.bank.controller;

import java.math.BigDecimal;


public class ObjectReceiver {

    public ObjectReceiver() {}

    public BigDecimal amount;

    public Long transactionId;
    public Long userId;
    public BigDecimal balance;

    public Long getUserId() {
        return userId;
    }


    public BigDecimal getBalance() {
        return balance;
    }

}