package com.kaushik.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Customer extends User {
    @Id
    private String customerId = UUID.randomUUID().toString();
    private Account account;

    public Customer(String name, String email, String password, String address, Account account) {
        super(name, email, password, address);
        this.account = account;
    }

    public void display() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + getAddress());
    }
}
