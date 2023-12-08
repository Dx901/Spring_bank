package com.dyes.bank.services;

import com.dyes.bank.exceptions.AccountNotFoundException;
import com.dyes.bank.models.Account;
import com.dyes.bank.models.User;
import com.dyes.bank.repository.AccountRepository;
import com.dyes.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    public AccountRepository accountRepository;

    public  List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

   @Autowired
    public AccountService(AccountRepository accountRepository) {
       this.accountRepository = accountRepository;
   }



    //creating an account
    public Account createAccount(User user, BigDecimal balance) {
       Account account = new Account(user, balance);
       return accountRepository.save(account);
    }

    //get an account by id
    public Account getAccountById(Long accountId) {
       return accountRepository.findById(accountId)
               .orElseThrow(() -> new AccountNotFoundException("No account with that ID"));
    }

    public void updateAccount(Long accountId, Account updatedAccount) {
       Account existingAccount = getAccountById(accountId);
       existingAccount.setBalance(updatedAccount.getBalance());
       accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long accountId) {
       Account account = getAccountById(accountId);
       accountRepository.delete(account);
    }

    private Long generateAccountNumber() {
        Random random = new Random();
        int randomSuffix = random.nextInt();
        return Long.valueOf(randomSuffix);
    }

}
