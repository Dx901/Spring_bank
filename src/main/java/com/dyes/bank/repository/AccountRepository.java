package com.dyes.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dyes.bank.models.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.accountNumber = :accountId")
    void updateBalance(@Param("accountId") Long accountId, @Param("balance") BigDecimal balance);

}
