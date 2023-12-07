package com.dyes.bank.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    public Long userId;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    public String name;

    public User() {}

    public User(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.userId;
    }



    public void setId(Long id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public Long getUserId() {
        return userId;
    }

    //This ensures yuo add an account to a user when createing a user.
    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }
}
