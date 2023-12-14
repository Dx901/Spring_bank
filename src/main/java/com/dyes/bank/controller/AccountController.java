package com.dyes.bank.controller;

import com.dyes.bank.exceptions.AccountNotFoundException;
import com.dyes.bank.models.Account;
import com.dyes.bank.services.AccountService;
import com.dyes.bank.services.UserService;
import com.dyes.bank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/accounts", produces = "application/json")
public class AccountController {

    @Autowired
    public AccountService accountService;
    public UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService){
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping
    public List<Account> getAllAccounts() { return accountService.getAllAccounts();}

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createaccount")
    public ResponseEntity<Account> createAccount(@RequestBody ObjectReceiver request) {
        Long userId = request.getUserId();
        BigDecimal balance = request.getBalance();

        User user = userService.getUserById(userId);
        Account createdAccount = accountService.createAccount(user, balance);

        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }





    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount) {
        try {
            accountService.updateAccount(accountId, updatedAccount);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}