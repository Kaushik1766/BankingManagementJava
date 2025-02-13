package com.kaushik.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends User {
    @Id
    private String customerId = UUID.randomUUID().toString();
    private String accountNumber;

    public Customer(String name, String email, String password, String address, String accountNumber) {
        super(name, email, password, address);
        this.accountNumber = accountNumber;
    }

    public void display() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + getAddress());
    }
}
