package com.dyes.bank.repository;

import com.dyes.bank.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
