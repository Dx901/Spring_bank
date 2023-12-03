package com.dyes.bank.user;

import java.util.Objects;

import jakarta.persistence.*;


@Entity

public class User {

    private @Id Long id;
    private String name;
    private int accountNumber;

    User() {}

    public User(String name, int accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
                && Objects.equals(this.accountNumber, user.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.accountNumber);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", accountNo='" + this.accountNumber + '\'' + '}';
    }

}
