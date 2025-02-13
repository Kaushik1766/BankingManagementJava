package com.kaushik.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaushik.models.Transaction;

public interface TransactionDB extends JpaRepository<Transaction, String> {

}
