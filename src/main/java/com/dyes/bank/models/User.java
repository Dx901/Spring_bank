package com.dyes.bank.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")

    public Long userId;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    public String name;
    public Integer idNumber;

    public User() {}


    public String getName() {
        return this.name;
    }

    public Integer getIdNumber() {
        return this.idNumber;
    }


    public void setIdNumber(Integer id) {
        this.idNumber = id;
    }

    public void setName(String name) {
        this.name = name;

    }


    //This ensures yuo add an account to a user when createing a user.
    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }
}
