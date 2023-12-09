package com.dyes.bank.constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL;

    public Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return getAmount();
    }

    public TransactionType getTransactionType(Long accountId) {
        return getTransactionType(accountId);
    }
}
