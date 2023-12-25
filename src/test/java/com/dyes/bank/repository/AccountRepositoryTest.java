package com.dyes.bank.repository;


import com.dyes.bank.models.Account;
import com.dyes.bank.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
