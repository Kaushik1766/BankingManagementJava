package com.kaushik.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Entity
public class Account {
    @Id
    private String accountNumber = UUID.randomUUID().toString();
    private double balance;
    private double pendingLoan;

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Pending Loan: " + pendingLoan);
    }
}
