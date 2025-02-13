package com.kaushik.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    private String transactionId;
    private Account from;
    private Account to;
    private double amount;

}
