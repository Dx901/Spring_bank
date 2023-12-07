package com.dyes.bank.services;

import com.dyes.bank.repository.AccountRepository;
import com.dyes.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AccountRepository accountRepository;

//    @Autowired
//    public TransactionService transactionService;
}
