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

    public User() {}


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;

    }

}
