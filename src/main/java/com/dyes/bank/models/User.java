package com.dyes.bank.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public int idNumber;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Account> accounts;

    public String name;

    public User() {}


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int i) {
        this.idNumber = idNumber;
    }
}