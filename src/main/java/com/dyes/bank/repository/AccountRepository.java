package com.dyes.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dyes.bank.models.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
