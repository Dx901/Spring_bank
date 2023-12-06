package com.dyes.bank.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity

public class User {

    public @Id Long userId;
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

}
