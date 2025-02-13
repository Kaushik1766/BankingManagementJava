package com.kaushik.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {
    private String transactionId;
    private Account from;
    private Account to;
    private double amount;

}
