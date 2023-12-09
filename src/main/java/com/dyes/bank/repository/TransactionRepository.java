package com.dyes.bank.repository;

import com.dyes.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Type> {
}
