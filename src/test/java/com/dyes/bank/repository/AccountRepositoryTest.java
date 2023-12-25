package com.dyes.bank.repository;


import com.dyes.bank.models.Account;
import com.dyes.bank.models.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import com.dyes.bank.repository.AccountRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveAccount() {
        //Given

        Account account = new Account();
        account.setBalance(new BigDecimal("1000.00"));

        //When
        Account savedAccount = accountRepository.save(account);

        //Then
        assertNotNull(savedAccount.getId());
        assertEquals(account.getBalance(), savedAccount.getBalance());
    }

    @Test
    public void findAccountById() {
        //Given
        Account account = new Account();
        account.setBalance(new BigDecimal("500.00"));
        Account savedAccount = accountRepository.save(account);

        //When
        Account retrievedAccount = accountRepository.findById(savedAccount.getId()).orElse(null);

        //Then
        assertNotNull(retrievedAccount);
        assertEquals(savedAccount.getId(), retrievedAccount.getId());
        assertEquals(savedAccount.getBalance(), retrievedAccount.getBalance());
    }

//    @Test
//    public void updateAccountBalance_shouldReturnUpdate() {
//        //Given
//        Account account = new Account();
//        account.setBalance(new BigDecimal("1000.00"));
//        Account savedAccount = accountRepository.save(account);
//
//        //When
//        accountRepository.updateBalance(savedAccount.getId(), new BigDecimal("1500.00"));
//
//        //Then
//        Account updatedAccount = accountRepository.findById(savedAccount.getId()).orElse(null);
//        assertNotNull(updatedAccount);
//        assertEquals(new BigDecimal("1500.00"), updatedAccount.getBalance());
//    }




    @Test
    public void deleteAccount_shouldDeleteAccount() {
        //Given
        Account account = new Account();
        account.setBalance(new BigDecimal("1000.00"));
        Account savedAccount = accountRepository.save(account);

        //When
        accountRepository.delete(savedAccount);

        //Then
        assertFalse(accountRepository.existsById(savedAccount.getId()));
    }
}
