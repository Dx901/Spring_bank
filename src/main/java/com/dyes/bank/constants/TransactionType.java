package com.dyes.bank.constants;

public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL;

    public Long getUserId() {

        if (this.equals(DEPOSIT)) {
            return 123L; // Replace with the actual user ID for deposit transactions
        } else if (this.equals(WITHDRAWAL)) {
            return 456L; // Replace with the actual user ID for withdrawal transactions
        }
        throw new UnsupportedOperationException("Unsupported transaction type");
    }
}
