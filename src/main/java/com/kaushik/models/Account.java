package com.kaushik.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class Account {
    @Id
    private String accountNumber = UUID.randomUUID().toString();
    private double balance;
    private double pendingLoan;
    private String customerId;

    public Account(String customerId) {
        this.customerId = customerId;
        this.balance = 0;
        this.pendingLoan = 0;
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Pending Loan: " + pendingLoan);
    }
}
